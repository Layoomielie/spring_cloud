package com.example.controller;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/5 16:29
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：张鸿建
 * @time：2019/6/5
 * @desc：
 **/
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HelloController {

    @ApiOperation(value = "hello", notes = "获取hello信息")
    @ApiImplicitParam(name = "name", value = "用户名字", required = true, dataType = "String")
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }


    @ApiOperation(value = "/home", notes = "获取home信息")
    @GetMapping("home")
    public String home() {
        return "home";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("auth")
    public String roleAuth() {
        return "auth";
    }

    // 进入方法前进行参数校验
    @PreAuthorize("hasRole('ROLE_ADMIN') and principal.username.equals(#username) and #id<10")
    // 返回时进行参数校验
    @PostAuthorize("returnObject%2==0")
    @GetMapping("info")
    public int info(int id, String username) {
        return 20;
    }


    //对集合方式的参数校验
    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @GetMapping("info/list")
    public List<Integer> infoList(List<Integer> idList) {
        return idList;
    }
}
