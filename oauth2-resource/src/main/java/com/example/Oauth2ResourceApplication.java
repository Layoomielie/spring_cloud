package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
@MapperScan("com.example.dao")
public class Oauth2ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourceApplication.class, args);
    }

}
