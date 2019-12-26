package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:27
 * @desc：
 **/
@Data
public class User implements Serializable {

    public static String salt = "-layoomielie";
    private String uid;
    private String username;
    private String password;

    public User() {
    }

    public User(String uid,String username, String password) {
        this.uid=uid;
        this.username = username;
        this.password = password;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
