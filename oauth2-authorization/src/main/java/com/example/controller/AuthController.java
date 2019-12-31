package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/27 18:22
 * @desc：
 **/
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userInfo")
    public ReturnResult getUserInfo(String username) {
        Optional<List<User>> optional = userService.getUserByUserName(username);
        if (optional.isPresent()) {
            List<User> users = optional.get();
            return ReturnResult.ok(users.get(0));
        }else {
            return ReturnResult.error("当前用户不存在");
        }
    }
}
