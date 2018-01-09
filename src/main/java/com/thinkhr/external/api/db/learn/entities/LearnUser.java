package com.thinkhr.external.api.db.learn.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "mdl_user")
@Data
@DynamicInsert
@DynamicUpdate
public class LearnUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="confirmed")
    private Integer confirmed = 1;
    
    @Column(name="thrcontactid")
    private Integer thrUserId;
    
    @Column(name="deleted")
    private Integer deleted;
    
    @Column(name="mnetHostId")
    private Integer mnetHostId = 1;
    
    @Column(name="username")
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @Column(name="firstname")
    private String firstName;
    
    @Column(name="lastname")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    @Column(name="phone1")
    @Size(max = 20)
    private String phone1;
    
    @Column(name="companyid")
    private Long companyId ;
    
    @Column(name="jobtitle")
    private String jobTitle;
    
    @Column(name="bounced")
    private Integer bounced;
    
    @Column(name="blockedaccount")
    private Integer blockedAccount;
    
    @OneToMany(mappedBy= "learnUser" , cascade = CascadeType.ALL)
    private List<LearnUserRoleAssignment> roleAssignments;
}