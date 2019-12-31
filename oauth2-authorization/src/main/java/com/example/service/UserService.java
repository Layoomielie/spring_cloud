package com.example.service;

import com.example.dao.UsersDao;
import com.example.entity.Users;
import com.example.query.UsersExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/27 17:54
 * @desc：
 **/
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UsersDao userDao;

    public Optional<List<Users>> getUserByUserName(String username) {
        if (StringUtils.isEmpty(username)) {
            logger.error("当前查询用户为空");
            return null;
        }
        UsersExample userExample = new UsersExample();
        UsersExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        //List<User> users=new ArrayList<>();
        //User user = userDao.selectByPrimaryKey(1);
        //users.add(user);
        List<Users> users = userDao.selectByExample(userExample);
        return Optional.of(users);
    }
}
