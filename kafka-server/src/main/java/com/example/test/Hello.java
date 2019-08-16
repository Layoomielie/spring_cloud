package com.example.test;

import org.springframework.stereotype.Component;

/**
 * @author：张鸿建
 * @time：2019/7/27 16:49
 * @desc：
 **/
@Component
public class Hello {
    private String name;


    public Hello(String name) {
        this.name=name;
    }
}
