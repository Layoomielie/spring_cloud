package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author：张鸿建
 * @time：2019/12/6 15:09
 * @desc：
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
//    private //在这里注入mapper，再想ia面根据用户名做信息查找

    /**
     * 重写PasswordEncoder  接口中的方法，实例化加密策略
     * @return 返回 BCrypt 加密策略
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;



    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 加载用户数据 , 返回UserDetail 实例
     * @param username  用户登录username
     * @return  返回User实体类 做用户校验
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名:"+username);
        String password = passwordEncoder.encode("123456");
        //User三个参数   (用户名+密码+权限)
        //根据查找到的用户信息判断用户是否被冻结
        logger.info("数据库密码:"+password);
        return new User(username,password,
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }

}