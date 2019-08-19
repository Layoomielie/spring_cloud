package com.example.util;

import java.util.UUID;

/**
 * @author：张鸿建
 * @time：2019/8/19 9:55
 * @desc：
 **/
public class StringUtils {
    public static String getUUid() {
        //注意replaceAll前面的是正则表达式
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}
