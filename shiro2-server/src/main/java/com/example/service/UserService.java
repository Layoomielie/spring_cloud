package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.query.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<User> users = userDao.selectByExample(userExample);
        if(users!=null){
            return Optional.of(users.get(0));
        }else {
            return Optional.of(null);
        }

    }

    public Optional<User> getUserByUid(int uid) {
        User user = userDao.selectByPrimaryKey(uid);
        return Optional.of(user);
    }
}
