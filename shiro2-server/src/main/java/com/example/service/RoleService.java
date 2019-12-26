package com.example.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author：张鸿建
 * @time：2019/12/25 14:38
 * @desc：
 **/
@Service
public class RoleService {

    public Set<String> findRoleByUserId(String userId) {
        Set<String> set = new HashSet<>();
        set.add("teacher");
        return set;
    }
}
