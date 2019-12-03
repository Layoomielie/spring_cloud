package com.example.config;

import com.example.handle.MyAuthenticationFailureHandler;
import com.example.handle.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author：张鸿建
 * @time：2019/12/3 15:18
 * @desc：配置账号和权限
 **/
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    
    /**
    * @param auth
    * @Author: 张鸿建
    * @Date: 2019/12/3
    * @Desc: 配置账号权限
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 添加root 账号
        auth.inMemoryAuthentication().withUser("root").password("123456").authorities("showOrder","addOrder","updateOrder","deleteOrder");

        // 添加admin 账号
        auth.inMemoryAuthentication().withUser("userAdd").password("123456").authorities("showOrder");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 配置查询订单权限
                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/login").permitAll()
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
                /*.antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login").
                successHandler(successHandler).failureHandler(failureHandler)*/
                .and().csrf().disable();   //csrf跨站点攻击关闭 否则必须要传递token！！
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
