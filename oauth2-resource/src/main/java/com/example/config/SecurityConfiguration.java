package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author：张鸿建
 * @time：2019/12/31 15:56
 * @desc：
 **/
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private DataSource dataSource;

    @Override
    @Bean(name = "AuthenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //String passwordEncode = new MyPasswordEncoder().encode("123456");
        String passwordEncode = bCryptPasswordEncoder.encode("456789");

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password(passwordEncode).authorities("USER").build());
        manager.createUser(User.withUsername("admin").password(passwordEncode).authorities("USER").build());
        return manager;
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.jdbcUserDetailsService()).passwordEncoder(this.passwordEncoder());

        //auth.userDetailsService(this.userDetailsService()).passwordEncoder(this.passwordEncoder());
        //auth.userDetailsService(oauth2ClientServiceDetail).passwordEncoder(this.passwordEncoder());
        //auth.userDetailsService(jdbcUserDetailsService()).passwordEncoder(this.passwordEncoder());
        //auth.userDetailsService(oauth2ClientServiceDetail).passwordEncoder(this.passwordEncoder());
    }

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

     @Bean
    protected UserDetailsService jdbcUserDetailsService() {
        //String passwordEncode = new MyPasswordEncoder().encode("123456");
        //String passwordEncode = bCryptPasswordEncoder.encode("123456");
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }

}
