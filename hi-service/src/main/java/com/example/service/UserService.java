package com.example.service;

import com.example.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author：张鸿建
 * @time：2019/8/7 17:50
 * @desc：
 **/
@Service
public class UserService {

        public User findUserById(String userId){
            if(userId!=null&&userId=="1"){
                User user = new User();
                user.setId("1");
                user.setPassword("111");
                return user;
            }else {
                return null;
            }
        }
}
