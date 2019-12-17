package com.example.config;

import com.example.handle.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author：张鸿建
 * @time：2019/12/17 10:41
 * @desc：
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String ROLE_ADMIN = "ADMIN";

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                //请求权限配置
                .authorizeRequests()
                //下边的路径放行,不需要经过认证
                .antMatchers("/oauth/*", "/auth/user/login").permitAll()
                //OPTIONS请求不需要鉴权
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //用户的增删改接口只允许管理员访问
                .antMatchers(HttpMethod.POST, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/auth/user").hasAnyAuthority(ROLE_ADMIN)
                //获取角色 权限列表接口只允许系统管理员及高级用户访问
                .antMatchers(HttpMethod.GET, "/auth/role").hasAnyAuthority(ROLE_ADMIN)
                //其余接口没有角色限制，但需要经过认证，只要携带token就可以放行
                .anyRequest()
                .authenticated();*/


        http.requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated().and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }
}
