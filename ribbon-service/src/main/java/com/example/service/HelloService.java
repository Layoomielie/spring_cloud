package com.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhanghongjian
 * @Date 2019/5/8 9:31
 * @Description
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name)
    {
        return restTemplate.getForObject("http://hi-service/hi?name=" + name, String.class);
    }


    public String hiError(String name){
        return "hiError Hystrix";
    }
}
