package com.example.entity;

/**
 * @author：张鸿建
 * @time：2019/8/7 17:47
 * @desc：
 **/
public class User {
    String Id;
    String username;
    String password;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
