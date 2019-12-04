package com.example.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：张鸿建
 * @time：2019/7/17 15:09
 * @desc：
 **/
@RequestMapping("/user")
@Controller
public class UserController {
    @RequiresPermissions("user:admin")
    @ResponseBody
    @RequestMapping("/show")
    public String showUser() {
        return "这是学生信息";
    }
}
