package com.example.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 配置更改时会得到及时消息处理
// http://localhost:8775/actuator/refresh
//@RefreshScope
public class ConfigController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String eurekaUrl;

    @Value("${server.port}")
    private String port;


    @RequestMapping("/config")
    public String getConfig(){
        String info="applicationName:"+applicationName+"               eurekaUrl:"+eurekaUrl+"               port:"+port;
        return info;
    }




}
