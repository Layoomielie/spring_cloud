package com.example.interrupt;

/**
 * @author：张鸿建
 * @time：2019/8/13 20:21
 * @desc：基础线程
 **/
public class MyThread1 extends  Thread{

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50000; i++) {
            System.out.println("i = "+i);
        }
    }
}
