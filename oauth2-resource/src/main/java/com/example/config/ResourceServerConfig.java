package com.example.config;

import com.example.handle.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Override//  .authenticated()
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and().httpBasic();
    }

    /* @Bean
     public ResourceServerTokenServices tokenServices(){
         RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
         String secret = bCryptPasswordEncoder.encode(oAuth2ClientProperties.getClientSecret());
         remoteTokenServices.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenAccess());
         remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
         remoteTokenServices.setClientSecret(secret);
         remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
         return remoteTokenServices;
     }
     @Bean
     public AccessTokenConverter accessTokenConverter() {
         return new DefaultAccessTokenConverter();
     }*/
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }
}
