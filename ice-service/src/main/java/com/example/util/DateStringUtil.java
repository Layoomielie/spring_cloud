package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author：张鸿建
 * @time：2019/10/29 19:52
 * @desc：
 **/
public class DateStringUtil {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static String getThisDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date end = c.getTime();
        String dqrq = format.format(end);//当前日期
        return dqrq;
    }

    public static String getThisYestoday() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String qyt = format.format(start);//前一天
        return qyt;
    }

    public static String getLastMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date start = c.getTime();
        String startDay = format.format(start);//前一月
        return startDay;
    }

    public static String getLastYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date start = c.getTime();
        String startDay = format.format(start);//前一年
        return startDay;
    }
}
