package com.example.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author：张鸿建
 * @time：2019/7/17 17:19
 * @desc：
 **/
//获取配置属性前缀
@Configuration
public class SecurityProperties {
    /**
     * 浏览器 属性类
     */

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}

