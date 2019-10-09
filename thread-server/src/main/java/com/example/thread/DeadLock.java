package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/10/9 19:29
 * @desc：
 **/
public class DeadLock {

    private static String A="A";
    private static String B="B";

    private void testDeadThreadLock(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println(11111);
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println(22222);
                    }
                }
            }
        });

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

    public static void main(String[] args) {
        new DeadLock().testDeadThreadLock();
    }
}


