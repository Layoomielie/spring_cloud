package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dao")
public class IceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IceServiceApplication.class, args);
    }

}
