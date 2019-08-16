package com.example.controller;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/5 16:29
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/6/5
 * @desc：
 **/
@RestController
public class HelloController {
    @ApiOperation(value = "hello",notes = "获取hello信息")
    @ApiImplicitParam(name = "name",value = "用户名字",required = true,dataType = "String")
    @GetMapping("hello")
    public String hello(String name){
        return name;
    }
    @ApiOperation(value = "home",notes = "获取home信息")
    @GetMapping("home")
    public String home(){
        return "home";
    }
}
