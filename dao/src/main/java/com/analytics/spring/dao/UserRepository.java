package com.analytics.spring.dao;

import com.analytics.spring.model.Role;
import com.analytics.spring.model.User;

import java.util.List;

/**
 * Created by Dmitry Natalenko on 28.04.2015.
 */
public interface UserRepository {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);


    User getUserById(Long id);

    User getUserByLogin(String login);

    List<User> getAllUsers();

    List<Role> getRolesByUserId(Long id);
}
