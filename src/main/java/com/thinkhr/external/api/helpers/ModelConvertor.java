package com.thinkhr.external.api.helpers;

import static com.thinkhr.external.api.services.CompanyService.getAuthorizationKeyFromCompanyId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thinkhr.external.api.ApplicationConstants;
import com.thinkhr.external.api.db.entities.Company;
import com.thinkhr.external.api.db.entities.CompanyContract;
import com.thinkhr.external.api.db.entities.CompanyProduct;
import com.thinkhr.external.api.db.entities.Location;
import com.thinkhr.external.api.db.entities.User;
import com.thinkhr.external.api.db.learn.entities.LearnCompany;
import com.thinkhr.external.api.db.learn.entities.LearnUser;
import com.thinkhr.external.api.services.LearnCompanyService;
import com.thinkhr.external.api.services.utils.CommonUtil;

/**
 * Model convertor
 * 
 * @author Surabhi Bhawsar
 * @Since 2017-12-14
 *
 */
@Service
public class ModelConvertor {

    
    /**
     * To convert ThroneCompany to LearnCompany. 
     * 
     * TODO: Use modelMapper, getting some issue so reverted code related with modelMapper.
     * 
     * @param company
     * @return
     */
    public LearnCompany convert(Company company) {
        
        LearnCompany learnCompany =  new LearnCompany() ;
        Location location = company.getLocation();
        if (location != null) {
            learnCompany.setAddress(location.getAddress());
            learnCompany.setAddress2(location.getAddress2());
            learnCompany.setCity(location.getCity());
            learnCompany.setState(location.getState());
            learnCompany.setZip(location.getZip());
        }
        
        learnCompany.setBroker(String.valueOf(company.getBroker()));
        learnCompany.setCompanyId(company.getCompanyId());
        learnCompany.setCompanyName(company.getCompanyName());
        learnCompany.setCompanyType(company.getCompanyType());
        learnCompany.setPhone(company.getCompanyPhone());
        Date now = new Date();
        learnCompany.setTimeCreated(now.getTime());
        learnCompany.setTimeModified(now.getTime());

        
        return learnCompany;

    }
    
    /**
     * method to get List of learnCompany field values for bulk upload
     * 
     * @param company
     * @return
     */
    public static List<Object> getColumnsForInsert(Company company) {
        if (company.getLocation() == null) {
            company.setLocation(new Location());
        }
        String inactiveCompanyName = LearnCompanyService.generateCompanyNameForInactive(company.getCompanyName(),
                company.getBroker(), company.getCompanyId());
        List<Object> learnCompanyFields = new ArrayList<Object>(Arrays.asList(
                company.getCompanyId(), 
                inactiveCompanyName,
                company.getCompanyType(),
                LearnCompanyService.generateCompanyKey(company.getCompanyId()),
                company.getLocation().getAddress(),
                company.getLocation().getAddress2(),
                company.getLocation().getCity(),
                company.getLocation().getState(),
                company.getLocation().getZip(),
                company.getBroker(),
                company.getCompanyPhone(),
                "1",
                String.valueOf(new Date().getTime()),
                String.valueOf(new Date().getTime())));
        
        return learnCompanyFields;
    }
    
    /**
     * method to convert throneUser to learnUser
     * 
     * @param throneUser
     * @return
     */
    public LearnUser convert(User throneUser) {

        LearnUser learnUser = new LearnUser();
        learnUser.setBlockedAccount(throneUser.getBlockedAccount());
        learnUser.setBounced(throneUser.getBounced());
        learnUser.setCompanyId(throneUser.getCompanyId());
        learnUser.setDeleted(throneUser.getDeleted());
        learnUser.setEmail(throneUser.getEmail());
        learnUser.setFirstName(throneUser.getFirstName());
        learnUser.setJobTitle(throneUser.getTitle());
        learnUser.setLastName(throneUser.getLastName());
        learnUser.setPassword(throneUser.getPassword());
        learnUser.setPhone1(throneUser.getPhone());
        learnUser.setThrUserId(throneUser.getUserId());
        learnUser.setUserName(throneUser.getUserName());

        return learnUser;
    }

    /**
     * method to convert Company to CompanyContract
     * 
     * @param company
     * @return
     */
    public CompanyContract convertToCompanyContract(Company company) {
        CompanyContract companyContract = new CompanyContract();
        companyContract.setCompanyId(company.getCompanyId());

        // TODO : Decide where to get productId from.
        companyContract.setProductId(ApplicationConstants.DEFAULT_PRODUCT_ID);
        companyContract.setStartDate(company.getCompanySince());

        // TODO : Decide how to get end date
        companyContract.setEndDate(company.getCompanySince());
        companyContract.setTempID(CommonUtil.getTempId());
        return companyContract;
    }

    /**
     * method to convert CompanyContract to CompanyProduct
     * 
     * @param company
     * @return
     */
    public CompanyProduct convertToCompanyProduct(CompanyContract companyContract) {
        CompanyProduct companyProduct = new CompanyProduct();
        companyProduct.setContractId(companyContract.getRelId());
        companyProduct.setCompanyId(companyContract.getCompanyId());
        companyProduct.setStartDate(companyContract.getStartDate());
        companyProduct.setAuthorizationKey(getAuthorizationKeyFromCompanyId(companyContract.getCompanyId()));

        // TODO : Decide where to get numberLicenses from. 
        companyProduct.setNumberLicenses(ApplicationConstants.DEFAULT_NUMBER_LICENSES);
        companyProduct.setTempID(CommonUtil.getTempId());
        return companyProduct;
    }

}
