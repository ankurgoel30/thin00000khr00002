DROP TABLE IF EXISTS `locations`;
CREATE TABLE `locations` (                               
             `locationID` int(11) NOT NULL AUTO_INCREMENT,          
             `Location_Type` varchar(20) DEFAULT NULL,              
             `Address` varchar(150) DEFAULT NULL,                   
             `Address2` varchar(50) DEFAULT NULL,                   
             `City` varchar(50) DEFAULT NULL,                       
             `State` varchar(2) DEFAULT NULL,                       
             `Zip` varchar(10) DEFAULT NULL,                        
             `Map_It` text,                                         
             `Client_ID` int(11) NOT NULL,                          
             `suite_floor` varchar(50) DEFAULT NULL,                
             `tempID` varchar(120) DEFAULT NULL,                    
             PRIMARY KEY (`locationID`)                             
);

DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (                                 
           `clientID` int(11) NOT NULL AUTO_INCREMENT,            
           `search_help` varchar(255) NOT NULL,                   
           `Client_Type` varchar(20) NOT NULL,                    
           `Client_Name` varchar(100) NOT NULL DEFAULT 'client',  
           `display_name` varchar(255) DEFAULT NULL,              
           `aspect` varchar(255) DEFAULT NULL,                    
           `Broker` int(11) DEFAULT NULL,                         
           `Client_Phone` varchar(40) DEFAULT NULL,               
           `Website` varchar(100) DEFAULT NULL,                   
           `Client_Since` timestamp NOT NULL DEFAULT '2017-01-01',     
           `tempID` varchar(50) DEFAULT NULL,                     
           `Client_Status` int(11) DEFAULT '1',                   
           `enhanced_password` int(11) DEFAULT '0',               
           `client_hours` varchar(255) DEFAULT '2',               
           `issuesBroker` int(11) DEFAULT NULL,                   
           `issuesClient` int(11) DEFAULT NULL,                   
           `issue_frequency` int(11) DEFAULT '1',                 
           `industry` varchar(255) DEFAULT NULL,                  
           `companySize` varchar(255) DEFAULT NULL,               
           `actualCompanySize` int(11) DEFAULT NULL,              
           `companyType` varchar(30) DEFAULT NULL,                
           `salesNotes` mediumtext,                               
           `customClient` int(11) DEFAULT '0',                    
           `groupID` varchar(60) DEFAULT NULL,                    
           `deactivationDate` timestamp DEFAULT NULL,                  
           `deactivationID` int(11) DEFAULT NULL,                 
           `login` int(11) DEFAULT '0',                           
           `producer` varchar(200) DEFAULT NULL,                  
           `specialDomain` varchar(255) DEFAULT NULL,             
           `addedBy` varchar(50) DEFAULT NULL,                    
           `channel` varchar(255) DEFAULT NULL,                   
           `directID` int(11) DEFAULT NULL,                       
           `resellerID` int(11) DEFAULT NULL,                     
           `parentID` int(11) DEFAULT NULL,                       
           `familiesID` int(11) DEFAULT NULL,                     
           `referral` varchar(50) DEFAULT NULL,                   
           `tally` int(11) DEFAULT '0',                           
           `optOut` int(11) DEFAULT '0',                          
           `optOutWelcome` int(1) DEFAULT '0',                    
           `newsletterID` int(11) DEFAULT '0',                    
           `newsletterPrivateLabel` int(1) DEFAULT '0',           
           `officeLocation` varchar(50) DEFAULT NULL,             
           `partnerClientType` varchar(50) DEFAULT NULL,          
           `marketID` int(11) DEFAULT NULL,                       
           `marketCode` varchar(70) DEFAULT NULL,                 
           `suspended` timestamp DEFAULT NULL,                     
           `marketingCampaign` varchar(120) DEFAULT NULL,         
           `marketingFree` int(5) DEFAULT NULL,                   
           `avoidTerms` int(11) DEFAULT '0',                      
           `custom1` varchar(255) DEFAULT NULL,                   
           `custom2` varchar(255) DEFAULT NULL,                   
           `custom3` varchar(255) DEFAULT NULL,                   
           `custom4` varchar(255) DEFAULT NULL,                   
           `custom5` varchar(255) DEFAULT NULL,                   
           `customDate` timestamp DEFAULT NULL,                    
           `noReporting` int(11) DEFAULT '0',                     
           `noTerms` int(1) DEFAULT '0',                          
           `expiryDate` timestamp DEFAULT NULL,                        
           `partnerAdmin` int(1) DEFAULT '0',                     
           `level` int(11) DEFAULT NULL,                          
           `brainID` varchar(25) DEFAULT NULL,                    
           `tokenID` varchar(25) DEFAULT NULL,                    
           `subscriptionID` varchar(30) DEFAULT NULL,             
           `posters` int(1) DEFAULT '0',                          
           `complyLinks` int(11) DEFAULT '1',                     
           `resources` int(11) DEFAULT '1',                       
           `newLook` int(11) DEFAULT '1',                         
           `customStyle` int(11) DEFAULT '0',                     
           `setup_fee` int(11) DEFAULT NULL,                      
           `customerSuccessManager` int(11) DEFAULT NULL,         
           `trial` int(11) DEFAULT NULL,                          
           `upsellLearn` int(11) DEFAULT '0',                     
           `sales_rep` varchar(60) DEFAULT NULL,                  
           `exported` int(11) DEFAULT '0',                        
           `direct_landing` int(11) DEFAULT NULL,                 
           `revenue` varchar(255) DEFAULT NULL,                   
           `workplaceUsers` int(11) DEFAULT NULL,                 
           `temp_Client_Status` int(11) DEFAULT NULL,             
           `Renewal_Date` date DEFAULT NULL,                      
           `re_manager` int(11) DEFAULT NULL,                     
           `partner_manager` int(11) DEFAULT NULL,                
           `auto_welcome_email` int(11) DEFAULT '1',              
           `contact_assignments` int(11) DEFAULT '0',             
           `salesforceID` varchar(50) DEFAULT NULL,               
           `special_note` longtext NOT NULL,                      
           `sourceID` int(11) DEFAULT '0',                        
           `t1_is_active` int(11) DEFAULT NULL,                   
           `t1_parent_company_id` int(11) DEFAULT NULL,           
           `t1_configuration_id` int(11) DEFAULT NULL,            
           `offering` varchar(50) DEFAULT NULL,                   
           `t1_customfield1` varchar(50) DEFAULT NULL,            
           `t1_customfield2` varchar(50) DEFAULT NULL,            
           `t1_customfield3` varchar(50) DEFAULT NULL,            
           `t1_customfield4` varchar(50) DEFAULT NULL,            
           `t1_display_name` varchar(50) DEFAULT NULL,            
           `t1_email_template_id` varchar(50) DEFAULT NULL,       
           PRIMARY KEY (`clientID`)                                
);

DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (                                                
            `salesforceID` varchar(50) DEFAULT NULL,                               
            `contactID` int(11) NOT NULL AUTO_INCREMENT,                           
            `accountID` varchar(255) DEFAULT NULL,                                 
            `search_help` varchar(255) NOT NULL DEFAULT '',                        
            `Contact_Type` varchar(20) DEFAULT '',                                 
            `decision_maker` int(11) DEFAULT '0',                                  
            `First_Name` varchar(50) NOT NULL DEFAULT '',                          
            `Last_Name` varchar(50) DEFAULT '',                                    
            `Location` varchar(8) DEFAULT '',                                      
            `Phone` varchar(25) DEFAULT '',                                        
            `International` int(1) DEFAULT NULL,                                   
            `Fax` varchar(25) DEFAULT '',                                          
            `Mobile` varchar(25) DEFAULT '',                                       
            `UserName` varchar(255) DEFAULT NULL,                                  
            `Email` varchar(255) DEFAULT NULL,                                     
            `Title` varchar(50) DEFAULT '',                                        
            `Client_ID` int(11) DEFAULT NULL,                                      
            `brokerID` int(11) DEFAULT NULL,                                       
            `client_hours` varchar(100) DEFAULT '',                                
            `Client_Status` enum('Contact','Inactive Contact') DEFAULT 'Contact',  
            `Client_Name` varchar(100) DEFAULT 'client',                           
            `active` int(1) DEFAULT '1',                                           
            `deactivationID` int(11) DEFAULT NULL,                                 
            `Password` varchar(25) DEFAULT '',                                     
            `password_reset` int(1) DEFAULT NULL,                                  
            `password_enc` varchar(100) DEFAULT NULL,                              
            `hrhID` int(8) DEFAULT '1',                                            
            `reminder` date DEFAULT NULL,                                          
            `firstMail` datetime DEFAULT NULL,                                     
            `activationDate` date DEFAULT NULL,                            
            `deactivationDate` date DEFAULT NULL,                                  
            `firstMailSuccess` varchar(50) DEFAULT NULL,                           
            `firstMailMessage` longtext,                                           
            `specialBlast` varchar(50) DEFAULT NULL,                               
            `master` int(11) DEFAULT '0',                                          
            `addedBy` varchar(60) DEFAULT NULL,                                    
            `Phone_Backup` varchar(25) DEFAULT NULL,                               
            `expirationDate` date DEFAULT NULL,                                    
            `mailStatus` int(11) DEFAULT NULL,                                     
            `mailTime` datetime DEFAULT NULL,                                      
            `bounced` int(11) DEFAULT '0',                                         
            `terms` datetime DEFAULT NULL,                                         
            `testAccount` int(1) DEFAULT '0',                                      
            `tempID` varchar(255) DEFAULT NULL,                                    
            `mkdate` varchar(20) NOT NULL,                                         
            `codevalid` varchar(50) NOT NULL,                                      
            `update_password` varchar(1) NOT NULL,                                 
            `learn_reminder` int(11) DEFAULT '0',                                  
            `master_backup` int(11) DEFAULT NULL,                                  
            `has_SPD` int(11) DEFAULT '0',                                         
            `learn_sync` int(11) DEFAULT '0',                                      
            `blockedAccount` int(11) NOT NULL,                                     
            `password_apps` varchar(255) DEFAULT NULL,                             
            `modified` int(11) DEFAULT NULL,                                       
            `deleted` int(11) DEFAULT NULL,                                        
            `t1_customfield1` varchar(255) DEFAULT NULL,                           
            `t1_customfield2` varchar(255) DEFAULT NULL,                           
            `t1_customfield3` varchar(255) DEFAULT NULL,                           
            `t1_customfield4` varchar(255) DEFAULT NULL,                           
            `t1_roleId` int(11) DEFAULT NULL,                                      
            PRIMARY KEY (`contactID`)                                                                                       
); 
