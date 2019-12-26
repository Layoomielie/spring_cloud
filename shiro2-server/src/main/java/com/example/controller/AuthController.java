package com.example.controller;

import com.example.dao.AuthorityDao;
import com.example.entity.Authority;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/26 10:15
 * @desc：
 **/
@RequestMapping("/api")
@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityDao authorityDao;

    @GetMapping("/user")
    public ReturnResult rootInfo(String uid) {
        Optional<User> optional = userService.getUserByUid(uid);
        if (optional.isPresent()) {
            return ReturnResult.ok(optional.get());
        } else {
            return ReturnResult.error("没有相关信息");
        }
    }

    @GetMapping("/auth")
    public ReturnResult authInfo(String uid) {
        List<Authority> authorities = authorityDao.selectByUserId(uid);
        if (authorities != null) {
            return ReturnResult.ok(authorities);
        } else {
            return ReturnResult.error("您的账号没有任何权限");
        }
    }
}
