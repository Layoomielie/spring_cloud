package com.example.config;

import com.example.entity.SecurityMatcher;
import com.example.handle.CustomAuthenticationEntryPoint;
import com.example.handle.MyAccessDeniedHandler;
import com.example.service.SecurityMatcherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/17 10:41
 * @desc：
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Resource
    OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

    @Resource
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Resource
    private SecurityMatcherService matcherService;

    @Override//  .authenticated()
    public void configure(HttpSecurity http) throws Exception {
        Optional<List<SecurityMatcher>> optional = matcherService.getAllMatchUrl();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authenticated = http.requestMatchers()
                .and()
                .authorizeRequests();
        if (optional.isPresent()) {
            List<SecurityMatcher> securityMatchers = optional.get();
            for (SecurityMatcher securityMatcher : securityMatchers) {
                String matchers = securityMatcher.getMatchers();
                String rolesName = securityMatcher.getRolesName();
                String authoritysName = securityMatcher.getAuthoritysName();
                if(StringUtils.isEmpty(matchers))continue;
                if(!StringUtils.isEmpty(rolesName)){
                     rolesName = rolesName.replace("，", ",");
                    if(rolesName.contains(",")){
                        String[] roles = rolesName.split(",");
                        authenticated=authenticated.antMatchers(matchers).hasAnyRole(roles);
                    }else {
                        authenticated=authenticated.antMatchers(matchers).hasRole(rolesName);
                    }
                }else if(!StringUtils.isEmpty(authoritysName)){
                    authoritysName = authoritysName.replace("，", ",");
                    if(authoritysName.contains(",")){
                        String[] authoritys = authoritysName.split(",");
                        authenticated=authenticated.antMatchers(matchers).hasAnyAuthority(authoritys);
                    }else {
                        authenticated=authenticated.antMatchers(matchers).hasAuthority(authoritysName);
                    }
                }else {
                    authenticated=authenticated.antMatchers(matchers).authenticated();
                }
            }

        }
        authenticated.and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler)
                .and().httpBasic();
    }


    /*@Bean(name = "myTokenService")
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder();

        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }*/

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //resources.tokenServices(tokenServices());

        super.configure(resources);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
