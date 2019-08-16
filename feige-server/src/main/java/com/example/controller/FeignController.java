package com.example.controller;

import com.example.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author zhanghongjian
 * @Date 2019/5/9 14:57
 * @Description
 */
@RestController
public class FeignController {

    @Autowired
    UserFeignClient userFeign;

    @RequestMapping("/say")
    public String say(@RequestParam String name){
        String home = userFeign.home(name);
        HashMap<Object, Object> hashMap = new HashMap<>();
        return home;
    }
}
