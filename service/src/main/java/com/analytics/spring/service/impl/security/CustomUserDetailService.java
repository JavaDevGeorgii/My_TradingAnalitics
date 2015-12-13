package com.analytics.spring.service.impl.security;

import com.analytics.spring.dao.UserRepository;
import com.analytics.spring.model.Role;
import com.analytics.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Natalenko on 11.05.2015.
 */
@Component("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(login);
        Long id = user.getId();
        List<Role> roleList = userRepository.getRolesByUserId(id);
        List<GrantedAuthority> authorities = getGrantedAuthorities(roleList);

        UserDetails userDetails =  new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                authorities);
        return userDetails;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Role > roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}

