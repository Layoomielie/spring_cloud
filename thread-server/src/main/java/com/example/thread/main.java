package com.example.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author：张鸿建
 * @time：2019/7/2 17:27
 * @desc：
 **/
public class main {
    public static void main(String[] args) {
        String dateStr="2019-07-01T17:33:25.000+08:00";
        dateStr="2019-07-02T17:35:39+08:00";
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX" );     // 北京
        //bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区
        try {
            Date date2 = bjSdf.parse(dateStr);
            String date3 = df2.format(date2);
            System.out.println(date3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
