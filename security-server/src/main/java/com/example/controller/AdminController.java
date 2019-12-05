package com.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/12/5 13:39
 * @desc：
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperation(value = "admin/info", notes = "获取info信息")
    @ApiImplicitParam()
    @GetMapping("/info")
    public String info() {
        return "只有admin权限以上的才可以访问";
    }
}
