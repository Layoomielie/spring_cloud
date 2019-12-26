package com.example.service;

import com.example.entity.User;
import com.example.realm.MyAuthorizingRealm;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:26
 * @desc：
 **/
@Service
public class UserService {

    public Optional<User> getUserByUserName(String userName) {
        String password = MyAuthorizingRealm.MD5Pwd(userName, "123456");
        User user = new User("1", "admin", password);
        System.out.println("加密后密码为："+password);
        return Optional.of(user);
    }


}
