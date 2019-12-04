package com.example.config;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/5 16:23
 */

import com.example.service.MyUserDetailService;
import com.example.utils.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author：张鸿建
 * @time：2019/6/5
 * @desc：
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/hello").permitAll().anyRequest().authenticated().and().logout().permitAll().and().formLogin();
        http.csrf().disable();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String passwordEncode = new MyPasswordEncoder().encode("123456");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("admin").password(passwordEncode).roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zhangsan").password(passwordEncode).roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("lisi").password(passwordEncode).roles("USER");

        // 自己配置用户校验认证
        //auth.userDetailsService(myUserDetailService);

        //使用spring表结构 users.ddl
        //auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("js/**", "images/**");
    }
}
