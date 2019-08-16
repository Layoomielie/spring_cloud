package com.example.util;

import java.util.List;

/**
 * @author：张鸿建
 * @time：2019/7/2 9:51
 * @desc：
 **/
public class Main {
    public static void main(String[] args) {
        String line="hello123qw123sadasdawq124sfas";
        String regex="\\d{3}";
        String s = RegexUtil.matchOne(regex, line);
        List<String> list = RegexUtil.matchAll(regex, line);
        System.out.println(list.toString());
    }
}
