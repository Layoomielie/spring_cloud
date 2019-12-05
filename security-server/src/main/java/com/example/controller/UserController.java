package com.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/12/5 13:37
 * @desc：
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "user/info", notes = "获取info信息")
    @ApiImplicitParam()
    @GetMapping("/info")
    public String info() {
        return "只有user权限以上的才可以访问";
    }


}
