package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.realm.MyAuthorizingRealm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:26
 * @desc：
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Optional<User> getUserByUserName(String userName) {
        String password = MyAuthorizingRealm.MD5Pwd(userName, "123456");
        User user = new User();
        user.setPassword(password);
        user.setUsername("admin");
        user.setUid("1");
        System.out.println("加密后密码为："+password);
        return Optional.of(user);
    }

    public Optional<User> getUserByUid(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return Optional.of(null);
        }
        User user = userDao.selectByPrimaryKey(uid);
        return Optional.of(user);
    }
}
