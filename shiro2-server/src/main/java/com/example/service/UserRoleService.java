package com.example.service;

import com.example.dao.UserRolesDao;
import com.example.entity.UserRoles;
import com.example.query.UserRolesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:38
 * @desc：
 **/
@Service
public class UserRoleService {

    @Autowired
    UserRolesDao userRolesDao;

    public Set<String> getUserRole(int uid){
        UserRolesExample userRolesExample = new UserRolesExample();
        UserRolesExample.Criteria criteria = userRolesExample.createCriteria();
        criteria.andUidEqualTo(uid);
        List<UserRoles> userRoles = userRolesDao.selectByExample(userRolesExample);
        Set<String> set = new HashSet<>();
        userRoles.forEach(userRole->{
            set.add(userRole.getRoleName());
        });
        return set;
    }

}
