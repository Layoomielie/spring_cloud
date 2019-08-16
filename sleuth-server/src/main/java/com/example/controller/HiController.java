package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/7/24 12:20
 * @desc：
 **/

@RestController
public class HiController {
    @RequestMapping("hi")
    public String sayHello(@RequestParam String name){
        return "hi! "+name ;
    }
}
