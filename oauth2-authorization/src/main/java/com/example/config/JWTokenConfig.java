package com.example.config;

import com.example.enhancer.JWTTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author：张鸿建
 * @time：2020/1/6 16:08
 * @desc：
 **/
@Configuration
public class JWTokenConfig {

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }



    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("test_key"); // 签名密钥OAuth2AuthenticationManager
        return accessTokenConverter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new JWTTokenEnhancer();
    }
}
