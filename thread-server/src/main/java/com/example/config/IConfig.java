package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/8/8 16:49
 * @desc：
 **/

@Component
@ConfigurationProperties(prefix = "head")
public class IConfig {

    public static Map imap;
    public static String str;

    public Map getImap() {
        return imap;
    }

    public void setImap(Map imap) {
        this.imap = imap;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Map getimap() {
        return imap;
    }

    public void setimap(Map imap) {
        this.imap = imap;
    }
}
