package com.example.suspend;

/**
 * @author：张鸿建
 * @time：2019/8/14 11:35
 * @desc：线程悬挂和恢复
 **/
public class demo1 {
    public static void main(String[] args) {
        try {
            MyThread1 thread = new MyThread1();
            thread.start();
            Thread.sleep(100);
            thread.suspend();
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(100);
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());

            thread.resume();
            Thread.sleep(100);
            thread.suspend();
            System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(100);
            System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
