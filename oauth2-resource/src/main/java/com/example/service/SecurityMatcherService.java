package com.example.service;

import com.example.dao.SecurityMatcherDao;
import com.example.entity.SecurityMatcher;
import com.example.query.SecurityMatcherExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author：张鸿建
 * @time：2020/1/14 16:31
 * @desc：
 **/
@Service
public class SecurityMatcherService {

    @Autowired
    SecurityMatcherDao securityMatcherDao;

    public Optional<List<SecurityMatcher>> getAllMatchUrl(){
        SecurityMatcherExample securityMatcherExample = new SecurityMatcherExample();
        securityMatcherExample.setOrderByClause("level");
        List<SecurityMatcher> securityMatchers = securityMatcherDao.selectByExample(securityMatcherExample);
        return Optional.of(securityMatchers);
    }
}
