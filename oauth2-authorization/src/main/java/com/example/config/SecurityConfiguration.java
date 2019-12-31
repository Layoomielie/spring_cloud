package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author：张鸿建
 * @time：2019/12/17 10:38
 * @desc：
 **/
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //@Autowired
    //private ClientDetails clientDetails;

    @Resource
    private DataSource dataSource;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**","/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and()
                .formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService()).passwordEncoder(this.passwordEncoder());
        //auth.userDetailsService(userDetailsService()).passwordEncoder(this.passwordEncoder());
        //auth.userDetailsService(oauth2ClientServiceDetail).passwordEncoder(this.passwordEncoder());
        auth.userDetailsService(jdbcUserDetailsService()).passwordEncoder(this.passwordEncoder());

    }

    @Bean
    protected UserDetailsService jdbcUserDetailsService() {
        //String passwordEncode = new MyPasswordEncoder().encode("123456");
        //String passwordEncode = bCryptPasswordEncoder.encode("123456");
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }
    //配置内存模式的用户
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //String passwordEncode = new MyPasswordEncoder().encode("123456");
        String passwordEncode = bCryptPasswordEncoder.encode("123456");

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("demoUser1").password(passwordEncode).authorities("USER").build());
        manager.createUser(User.withUsername("demoUser2").password(passwordEncode).authorities("USER").build());


        return manager;
    }

    /**
     * 需要配置这个支持password模式
     */
    @Override
    @Bean(name = "AuthenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    public MyPasswordEncoder myPasswordEncoder() {
        return new MyPasswordEncoder();
    }*/

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //demoAppSecret    $2a$10$gCWVItW4t8EbxQqR1unGB.yLMidXOfJTW2Lhjl.0yzwvosQXyDn1W
        //123456    $2a$10$3/5GT5se/lFSMlILrTF6ZOMueuFSUyBCWPa2mhu0SfOFWOCnLZ5uG
        //456789
        String encode = bCryptPasswordEncoder.encode("456789");
        System.out.println(encode);
    }

}
