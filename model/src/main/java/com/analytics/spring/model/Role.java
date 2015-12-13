package com.analytics.spring.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Dmitry Natalenko on 11.05.2015.
 */
@Entity
@Table(name = "USER_ROLES")
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String role;


    public Role() {
    }

    public Role(String role){
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
