package com.example.entity;

/**
 * @author：张鸿建
 * @time：2019/8/19 9:52
 * @desc：
 **/
public class LoginEntity {
    private String uuid;
    private String token;
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
