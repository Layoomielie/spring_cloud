package com.example.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:37
 * @desc：
 **/
@Service
public class AuthService {
    public Set<String> findAuthByUserId(String userId){
        Set<String> set = new HashSet<>();
        //list.add(new Authority("1","add"));
        set.add("user:show");
        set.add("user:admin");
        return set;
    }
}
