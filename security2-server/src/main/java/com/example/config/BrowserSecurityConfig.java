package com.example.config;

import com.example.handle.MyAuthenticationFailHandler;
import com.example.handle.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author：张鸿建
 * @time：2019/12/6 15:07
 * @desc：
 **/
@Configuration
public class BrowserSecurityConfig  extends WebSecurityConfigurerAdapter {

    /**
     * 注入 Security 属性类配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 重写PasswordEncoder  接口中的方法，实例化加密策略
     * @return 返回 BCrypt 加密策略
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入 自定义的  登录成功处理类
     */
    @Autowired
    private MyAuthenticationSuccessHandler mySuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myFailHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String redirectUrl = securityProperties.getBrowser().getLoginPage();
        //basic 登录方式
//      http.httpBasic()

        //表单登录 方式
        http.formLogin()
                .loginPage("/authentication/require")
                //登录需要经过的url请求
                .loginProcessingUrl("/authentication/form")
                .successHandler(mySuccessHandler)
                .failureHandler(myFailHandler)
                .and()
                //请求授权
                .authorizeRequests()
                //不需要权限认证的url
                .antMatchers("/authentication/require",redirectUrl).permitAll()
                //任何请求
                .anyRequest()
                //需要身份认证
                .authenticated()
                .and()
                //关闭跨站请求防护
                .csrf().disable();
    }
}
