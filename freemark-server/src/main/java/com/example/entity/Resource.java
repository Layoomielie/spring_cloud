package com.example.entity;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/21 19:21
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author：张鸿建
 * @time：2019/6/21
 * @desc：
 **/
@Configuration
//指定所读取的配置文件的路径
@PropertySource(value = "classpath:resource.properties")
public class Resource {

    private String name;
    private String website;
    private String language;

    //...setter and getter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
