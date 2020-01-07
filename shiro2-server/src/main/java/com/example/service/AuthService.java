package com.example.service;

import com.example.dao.AuthorityDao;
import com.example.entity.Authority;
import com.example.query.AuthorityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:37
 * @desc：
 **/
@Service
public class AuthService {

    @Autowired
    private AuthorityDao authorityDao;

    public Set<String> findAuthByRoleId(int rid) {
        AuthorityExample authorityExample = new AuthorityExample();
        AuthorityExample.Criteria criteria = authorityExample.createCriteria();
        criteria.andRoleIdEqualTo(rid);
        List<Authority> authorities = authorityDao.selectByExample(authorityExample);
        Set<String> set = new HashSet<>();
        for (Authority authority : authorities) {
            set.add(authority.getAuthName());
        }
        return set;
    }

    public Set<String> findAuthByUserId(int uid) {
        AuthorityExample authorityExample = new AuthorityExample();
        AuthorityExample.Criteria criteria = authorityExample.createCriteria();
        criteria.andUidEqualTo(uid);
        List<Authority> authorities = authorityDao.selectByExample(authorityExample);
        Set<String> set = new HashSet<>();
        for (Authority authority : authorities) {
            set.add(authority.getAuthName());
        }
        return set;
    }

}
