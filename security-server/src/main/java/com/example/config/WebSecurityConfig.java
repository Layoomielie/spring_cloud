package com.example.config;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/5 16:23
 */

import com.example.handle.MyAccessDeniedHandler;
import com.example.handle.MyAuthenticationFailureHandler;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author：张鸿建
 * @time：2019/6/5
 * @desc：声明登录方式 登录页面  声明  注册成功失败处理器
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    DataSource dataSource;

    @Autowired
    MyAuthenticationFailureHandler handler;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    //配置权限控制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/hello", "/swagger*/**", "/v2/**", "/webjars/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers().hasAnyRole()
                .anyRequest().authenticated().and().logout().permitAll()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).and().formLogin();
        http.csrf().disable();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置在内存中
        /*String passwordEncode = new MyPasswordEncoder().encode("123456");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("admin").password(passwordEncode).roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zhangsan").password(passwordEncode).roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("lisi").password(passwordEncode).roles("USER");*/

        // 自己配置用户校验认证
        //auth.userDetailsService(myUserDetailService);

        //使用spring表结构 users.ddl
        //auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from users WHERE username=?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username=?")
                .passwordEncoder(new MyPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("js/**", "images/**");
    }

    /**
     * @Author: 张鸿建
     * @Date: 2019/12/5
     * @Desc: 设置加密策略
     */
    //@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
