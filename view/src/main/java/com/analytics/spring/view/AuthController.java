package com.analytics.spring.view;

import com.analytics.spring.model.User;
import com.analytics.spring.service.UserService;
import com.analytics.spring.service.impl.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vitalii Ievtushenko on 09.05.2015 21:06.
 */
@Controller
public class AuthController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> doRegistration(@RequestBody User newUser) {
        User user = userService.createUser(newUser);
        String login = user.getLogin();
        String password =  user.getPassword();
        try {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(login);
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    login, password, userDetails.getAuthorities());

            if(auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                return new ResponseEntity<String>("OK", HttpStatus.OK);
            }
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin.page");
        return model;
    }

    @RequestMapping(value = "/403")
    public String accesssDenied() {
        return "403.page";
    }

    @RequestMapping(value = "/home")
    public String method() {
        return "home.page";
    }
}
