package com.example.service;

import com.example.entity.AuthOauth2Entity;
import com.example.entity.Authorities;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/27 17:41
 * @desc：
 **/
@Service
public class Oauth2ClientServiceDetail implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    AuthoritiesService authoritiesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<List<User>> Useroptional = userService.getUserByUserName(username);
        User user;
        if(Useroptional.isPresent()){
            List<User> users = Useroptional.get();
            user=users.get(0);
        }else {
            return null;
        }
        Optional<List<Authorities>> optional = authoritiesService.getAuthoritiesInfo(user.getUid());
        List<Authorities> authorities=new ArrayList<>();
        List<AuthOauth2Entity> authOauth2Entities = new ArrayList<>();
        if(optional.isPresent()){
             authorities = optional.get();
            authorities.forEach(authority -> {
                AuthOauth2Entity authOauth2Entity = new AuthOauth2Entity(authority.getAuthority());
                authOauth2Entities.add(authOauth2Entity);
            });
        }else {
            return null;
        }

        org.springframework.security.core.userdetails.User oauthUser = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authOauth2Entities);

        return oauthUser;
    }
}
