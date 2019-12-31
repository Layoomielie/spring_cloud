package com.example.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author：张鸿建
 * @time：2019/12/30 11:36
 * @desc：
 **/
public class AuthOauth2Entity implements GrantedAuthority{

    private String auth;

    public AuthOauth2Entity(String auth) {
        this.auth = auth;
    }

    @Override
    public String getAuthority() {
        return auth;
    }
}
