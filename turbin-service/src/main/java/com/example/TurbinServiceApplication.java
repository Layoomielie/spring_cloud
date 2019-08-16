package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
//@EnableTurbineStream
@EnableDiscoveryClient
//@EnableHystrixDashboard
public class TurbinServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbinServiceApplication.class, args);
    }

}
