package com.example.controller;

import com.example.entity.Filter;
import com.example.entity.User;
import com.example.service.AuthService;
import com.example.service.FilterService;
import com.example.service.UserService;
import com.example.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author：张鸿建
 * @time：2019/12/26 10:15
 * @desc：
 **/
@RequestMapping("/api")
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private FilterService filterService;

    @GetMapping("/user")
    public ReturnResult rootInfo(int uid) {
        Optional<User> optional = userService.getUserByUid(uid);
        if (optional.isPresent()) {
            return ReturnResult.ok(optional.get());
        } else {
            return ReturnResult.error("没有相关信息");
        }
    }

    @GetMapping("/auth")
    public ReturnResult authInfo(int uid) {
        Set<String> authorities = authService.findAuthByUserId(uid);
        if (authorities != null) {
            return ReturnResult.ok(authorities);
        } else {
            return ReturnResult.error("您的账号没有任何权限");
        }
    }

    @GetMapping("/filter")
    public ReturnResult filterInfo() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Optional<List<Filter>> optional = filterService.getFilterListByStatus(list);
        if (optional.isPresent()) {
            return ReturnResult.ok(optional.get());
        } else {
            return ReturnResult.error("失败");
        }
    }
}
