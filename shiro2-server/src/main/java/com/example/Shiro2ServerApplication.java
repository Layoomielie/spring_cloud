package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dao")
public class Shiro2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Shiro2ServerApplication.class, args);
    }

}
