package com.example.util;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/25 15:48
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author：张鸿建
 * @time：2019/6/25
 * @desc：
 **/
public class FileUtil {

    public static FileWriter getfileStream(String path) {

        File file = null;
        BufferedWriter bw = null;
        FileWriter fw=null;
        try {
            //path = "D:\\template\\hello.txt";
            file = new File(path);
            fw = new FileWriter(file);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return fw;
    }
}
