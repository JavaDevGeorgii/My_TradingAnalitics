package com.analytics.service.test;

import com.analytics.spring.dao.UserRepository;
import com.analytics.spring.model.User;
import com.analytics.spring.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Dmitry Natalenko on 01.08.2015.
 */


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("classpath:test-service.xml")
public class UserServiceTest {

    List<User> userList = Arrays.asList(new User());

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    @Autowired
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTeacherByIdTestMockito(){
        User source = new User();
        when(userRepository.getUserById(any(Long.class))).thenReturn(source);
        User user = userService.getUserById(1L);

        Assert.assertEquals(user, source);
    }




}
