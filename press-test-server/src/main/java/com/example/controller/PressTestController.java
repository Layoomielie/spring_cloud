package com.example.controller;

import com.example.util.PressureUtils;
import com.example.util.SpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/11/29 14:27
 * @desc：
 **/
@RestController
public class PressTestController {

    @GetMapping("/press/search")
    public String pressSearch(int pool,String indexName){
        PressureUtils pressureUtils = new PressureUtils(pool,indexName);
        pressureUtils.createQuery();
        return "创建完成";
    }
    @GetMapping("/press/simple/search")
    public String pressSimpleSearch(int pool ,String indexName){
        PressureUtils pressureUtils = new PressureUtils(pool,indexName);
        pressureUtils.createSimpleQuery();
        return "创建完成";
    }
}
