package com.example.interrupt;

/**
 * @author：张鸿建
 * @time：2019/8/13 20:41
 * @desc：
 **/
public class demo3 {
    public static void main(String[] args) {
        try {
            MyThread3 myThread = new MyThread3();
            myThread.start();
            Thread.sleep(200);
            myThread.interrupt();
            System.out.println("myThread是否停止 ： "+myThread.isInterrupted());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
