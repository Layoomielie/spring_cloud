package com.example.insecurity.demo1;

/**
 * @author：张鸿建
 * @time：2019/8/13 16:49
 * @desc：不共享数据
 **/
public class Run {

    public static void main(String[] args) {
        Mythread a = new Mythread("A");
        Mythread b = new Mythread("B");
        Mythread c = new Mythread("C");
        a.start();
        b.start();
        c.start();
    }
}
