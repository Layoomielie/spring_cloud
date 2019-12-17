package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/12/17 10:31
 * @desc：
 **/
@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/auth")
    public String getAuthCode(){
        System.out.println("auth sucess");
        return "auth sucess";
    }
}
