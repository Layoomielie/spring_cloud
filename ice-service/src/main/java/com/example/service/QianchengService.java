package com.example.service;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author：张鸿建
 * @time：2019/9/23 17:02
 * @desc：
 **/
public class QianchengService {

    @Cacheable(value = "sampleValue",key = "targetClass.getName()+'.'+methodName+'.'+#id")
    @CacheEvict
    public void test(){

    }
}
