package com.example.config;

import com.example.entity.LoginType;

/**
 * @author：张鸿建
 * @time：2019/7/17 17:19
 * @desc：
 **/
public class BrowserProperties {
    public BrowserProperties( ) {
        super();
    }

    public BrowserProperties(String loginPage, LoginType loginType) {
        this.loginPage = loginPage;
        this.loginType = loginType;
    }

    /**
     *  loginPage 默认值  是login.html
     *  如果 application.properties 里有对 fantJ.security.browser.loginPage 的声明，则获取该值
     */
    private String loginPage = "/browser-login.html";

    /**
     * 默认 返回 json 类型
     */
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
