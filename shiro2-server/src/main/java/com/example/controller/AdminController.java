package com.example.controller;

import com.example.util.ReturnResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：张鸿建
 * @time：2019/12/25 16:35
 * @desc：
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequiresPermissions("user:admin")
    @GetMapping("/admin/info")
    public ReturnResult getInfo(){
        return ReturnResult.ok("获取admin 信息");
    }

    @RequiresPermissions("user:root")
    @GetMapping("/root/info")
    public ReturnResult getRootInfo(){
        return ReturnResult.ok("root 信息");
    }

}
