package com.thinkhr.external.api.services.email;

import static com.thinkhr.external.api.ApplicationConstants.RESET_PASSWORD_PREFIX;
import static com.thinkhr.external.api.services.utils.CommonUtil.generateHashedValue;
import static com.thinkhr.external.api.services.utils.EmailUtil.BROKER_NAME;
import static com.thinkhr.external.api.services.utils.EmailUtil.FIRST_NAME;
import static com.thinkhr.external.api.services.utils.EmailUtil.SET_LOGIN_LINK;
import static com.thinkhr.external.api.services.utils.EmailUtil.SET_PASSWORD_LINK;
import static com.thinkhr.external.api.services.utils.EmailUtil.SUPPORT_EMAIL;
import static com.thinkhr.external.api.services.utils.EmailUtil.SUPPORT_PHONE;
import static com.thinkhr.external.api.services.utils.EmailUtil.USER_NAME;
import static com.thinkhr.external.api.services.utils.EmailUtil.prepareResetPasswordlink;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.springml.marketo.rest.client.LeadDatabaseClient;
import com.springml.marketo.rest.client.MarketoClientFactory;
import com.springml.marketo.rest.client.model.QueryResult;
import com.thinkhr.external.api.db.entities.Company;
import com.thinkhr.external.api.db.entities.User;
import com.thinkhr.external.api.exception.APIErrorCodes;
import com.thinkhr.external.api.exception.ApplicationException;
import com.thinkhr.external.api.model.EmailRequest;
import com.thinkhr.external.api.model.KeyValuePair;
import com.thinkhr.external.api.model.RequestCampaign;
import com.thinkhr.external.api.repositories.CompanyRepository;
import com.thinkhr.external.api.repositories.EmailTemplateRepository;
import com.thinkhr.external.api.repositories.SetPasswordRequestRepository;
import com.thinkhr.external.api.repositories.UserRepository;

import lombok.Data;

/**
 * EmailService class to work with email entities, repositories and Marketo APIs.
 * 
 * @author Surabhi Bhawsar
 * @since 2018-01-15
 *
 */
@Data
public class MarketoEmailService implements EmailService {
    
    private Logger logger = LoggerFactory.getLogger(MarketoEmailService.class);
    
    @Autowired
    private EmailTemplateRepository emailRepository;
    
    @Autowired
    private SetPasswordRequestRepository setPasswordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CompanyRepository companyRepository;

    @Value("${com.thinkhr.external.api.marketo.clientId}")
    private String clientId;

    @Value("${com.thinkhr.external.api.marketo.clientSecret}")
    private String clientSecret;

    @Value("${com.thinkhr.external.api.marketo.baseUri}")
    private String baseUri;
    
    @Value("${com.thinkhr.external.api.marketo.welcomeemail.campaignId}")
    private String campaignId;
    
    @Value("${default_support_email}")
    private String defaultSupportEmail;
    
    @Value("${default_support_phone}")
    private String defaultSupportPhone;
    
    @Value("${login_url}")
    private String loginUrl;
    
    @Override
    public EmailRequest createEmailRequest(Integer brokerId, List<String> userNames) {
        EmailRequest emailRequest = new EmailRequest();
        Company broker = null;
        if (brokerId != null) {
            broker = companyRepository.findOne(brokerId);
        }
        
        
        for(String username : userNames ) {
            User user = userRepository.findByUserName(username);
            if (user == null) {
                throw ApplicationException.createEntityNotFoundError(APIErrorCodes.ENTITY_NOT_FOUND, "username", username);
            }

            //String sendgridTemplateId = emailTemplate.getSendgridTemplateId();
            String generatedHashedCode = RESET_PASSWORD_PREFIX + generateHashedValue(user.getUserId());
            String resetPasswordLink = prepareResetPasswordlink(loginUrl, generatedHashedCode);


            List<KeyValuePair> substitutions = new ArrayList<KeyValuePair>();
            substitutions.add(createKeyValue(SET_LOGIN_LINK, loginUrl));
            substitutions.add(createKeyValue(FIRST_NAME, user.getFirstName()));
            substitutions.add(createKeyValue(BROKER_NAME, broker.getCompanyName()));
            substitutions.add(createKeyValue(USER_NAME, user.getUserName()));
            substitutions.add(createKeyValue(SUPPORT_PHONE, defaultSupportPhone));
            substitutions.add(createKeyValue(SUPPORT_EMAIL, defaultSupportEmail));
            substitutions.add(createKeyValue(SET_PASSWORD_LINK, resetPasswordLink));
            
            emailRequest.getRecipientToSubstitutionMap().put(user, substitutions);
        }
        
        return emailRequest;
    }

    @Override
    public void sendEmail(EmailRequest emailRequest) throws Exception {
     
        LeadDatabaseClient leadClient = MarketoClientFactory.getLeadDatabaseClient(clientId, clientSecret, baseUri);
        String accessToken = "token"; //TODO write code to get this
        
        for (User userToSendEmail : emailRequest.getRecipientToSubstitutionMap().keySet()) {
            RequestCampaign requestCampaign = new RequestCampaign(baseUri, campaignId);
            
            QueryResult result = leadClient.query("leads", "email", userToSendEmail.getEmail());
            Integer leadId = Integer.valueOf(result.getResult().get(0).get("id"));

            // Set tokens
            List<KeyValuePair> subtitutions = emailRequest.getRecipientToSubstitutionMap().get(userToSendEmail);
            if (subtitutions != null && !subtitutions.isEmpty()) {
                subtitutions.stream().forEach(keyValuePair -> {
                    requestCampaign.addToken(keyValuePair.getKey(), keyValuePair.getValue());
                });
            }
            
            requestCampaign.addLead(leadId);
            requestCampaign.postData(accessToken);
        }
    }
    
    /**
     * Create KeyValue pair
     * 
     * @param key
     * @param value
     * @return
     */
    private KeyValuePair createKeyValue(String key, String value) {
        return new KeyValuePair(key, value);
    }

}
