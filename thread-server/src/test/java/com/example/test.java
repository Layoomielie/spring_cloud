package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：张鸿建
 * @time：2019/7/1 20:32
 * @desc：
 **/
public class test {
    public static void main(String[] args) {
        String line="2019-0-1";
        String pattern="\\d{4}\\-\\d{1,2}\\-\\d{1,2}";

        boolean b = matches(pattern, line);
        System.out.println(b);
    }

    public static boolean matches(String regex, String content) {
        Matcher matcher = matcher(regex, content);
        boolean bool = matcher.matches();
        return bool;
    }
    /**
     * 返回一个mathcer
     *
     * @param regex
     * @param content
     * @return
     */
    public static Matcher matcher(String regex, String content) {
        return compile(regex, true).matcher(content);
    }

    /**
     * 编译一个正则表达式
     *
     * @param regex
     * @return
     */
    public static Pattern compile(String regex, boolean isInsensitive) {
        if (true == isInsensitive) {
            return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            return Pattern.compile(regex);
        }
    }
}
