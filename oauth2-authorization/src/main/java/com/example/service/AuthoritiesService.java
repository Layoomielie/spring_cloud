package com.example.service;

import com.example.dao.AuthoritiesDao;
import com.example.entity.Authorities;
import com.example.query.AuthoritiesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2019/12/30 11:24
 * @desc：
 **/
@Service
public class AuthoritiesService {

    @Autowired
    AuthoritiesDao authoritiesDao;

    public Optional<List<Authorities>> getAuthoritiesInfo(int uid){
        AuthoritiesExample authoritiesExample = new AuthoritiesExample();
        AuthoritiesExample.Criteria criteria = authoritiesExample.createCriteria();
        criteria.andUidEqualTo(uid);
        List<Authorities> authorities = authoritiesDao.selectByExample(authoritiesExample);
        return Optional.of(authorities);
    }
}
