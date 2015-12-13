package com.analytics.spring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry Natalenko on 29.04.2015.
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    private boolean enabled ;

//    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
//    private RoleEnum roleEnum;

    @OneToMany(targetEntity = Role.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles ;

//    @OneToMany(targetEntity = Deal.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Deal> deals;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    //    public RoleEnum getRoleEnum() {
//        return roleEnum;
//    }
//
//    public void setRoleEnum(RoleEnum roleEnum) {
//        this.roleEnum = roleEnum;
//    }
//
//
//    public List<Deal> getDeals() {
//        return deals;
//    }
//
//    public void setDeals(List<Deal> deals) {
//        this.deals = deals;
//    }

}
