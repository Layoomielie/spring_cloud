package com.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：张鸿建
 * @time：2019/7/2 9:51
 * @desc：
 **/
public class RegexUtil {

    //电话号码
    public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)" ;

    //手机号码
    public static final String MOBILE ="^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

    //身份证表达式
    public static final String IDCARD="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
            "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
            "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
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
     * 匹配第一个
     *
     * @param regex
     * @param content
     * @return
     */
    public static String matchOne(String regex, String content) {
        String match = null;
        Matcher m = matcher(regex, content);
        while (m.find()) {
            match = m.group().trim();
            break;
        }
        return match;
    }

    /**
     * 匹配第一个
     *
     * @param regex
     * @param content
     * @param regexGroup 正则分组号
     * @return
     */
    public static String matchOne(String regex, String content, int regexGroup) {
        String match = null;
        Matcher m = matcher(regex, content);
        while (m.find()) {
            match = m.group(regexGroup).trim();
            break;
        }
        return match;
    }

    /**
     * 匹配所有
     *
     * @param regex      正则表达式
     * @param regexGroup 分组号
     * @param content    要进行匹配的内容
     * @return
     */
    public static List<String> matchAll(String regex, String content, int regexGroup) {
        List<String> list = new LinkedList<String>();
        Matcher m = matcher(regex, content);
        while (m.find()) {
            list.add(m.group(regexGroup));
        }
        return list;
    }

    public static List<String> matchAll(String regex, String content) {
        List<String> list = new LinkedList<String>();
        Matcher m = matcher(regex, content);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    /**
     * 判断是否匹配，返回布尔值
     * 说明:
     *
     * @param regex
     * @param content
     * @return
     */
    public static boolean matches(String regex, String content) {
        Matcher matcher = matcher(regex, content);
        boolean bool = matcher.matches();
        return bool;
    }

    public static List<String> toList(String regex, String source) {
        List<String> list = new LinkedList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    /**
     * 检查email
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证身份证号码
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex,idCard);
    }

    /**
     * 验证整数（正整数和负整数）
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex,digit);
    }

    /**
     * 验证日期（年月日）
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex,birthday);
    }

}
