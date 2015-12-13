package com.analytics.spring.dao.impl;

import com.analytics.spring.dao.UserRepository;
import com.analytics.spring.model.Role;
import com.analytics.spring.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Dmitry Natalenko on 29.04.2015.
 */
@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }


    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByLogin(String  login) {
        Query query = entityManager.createQuery("SELECT u from User u WHERE u.login= :login");
        query.setParameter("login",login);
        List<User> users = query.getResultList();
        User user = users.get(0);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("from User a ");
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public List<Role> getRolesByUserId(Long id) {
        Query query = entityManager.createQuery("SELECT ur from Role ur WHERE ur.user.id= :id");
        query.setParameter("id",id);
        List<Role> roles = query.getResultList();
        return roles;
    }
}

