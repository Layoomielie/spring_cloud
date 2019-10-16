package com.example.test;

import java.util.ArrayList;

/**
 * @author：张鸿建
 * @time：2019/10/14 15:41
 * @desc：
 **/
public class test extends Thread{
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        test.addList(objects);
        System.out.println(objects);

    }
    static void addList(ArrayList list){
        list.add("BB");
    }
}
