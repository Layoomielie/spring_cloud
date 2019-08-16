package com.example.interrupt;

/**
 * @author：张鸿建
 * @time：2019/8/13 20:23
 * @desc：
 **/
public class demo1 {
    public static void main(String[] args) {
        try {

            MyThread1 myThread = new MyThread1();
            myThread.start();
            Thread.sleep(300);
            myThread.interrupt();
            System.out.println("myThread是否停止 ： "+myThread.isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("Thread是否停止 ： "+Thread.interrupted());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
