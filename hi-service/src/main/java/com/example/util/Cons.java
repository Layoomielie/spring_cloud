package com.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/8/8 14:57
 * @desc：
 **/
@Component
//@ConfigurationProperties设置前缀user
@ConfigurationProperties(prefix = "user")
public class Cons {

    static public Map audiences;

    @Value("${user.name}")
    static public String name;

    public Map getAudiences() {
        return audiences;
    }

    public void setAudiences(Map audiences) {
        this.audiences = audiences;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Cons.name = name;
    }
}

