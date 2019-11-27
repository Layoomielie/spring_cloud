package com.example.thread.lock;

/**
 * @author：张鸿建
 * @time：2019/11/26 15:42
 * @desc： 非静态锁和静态锁的区别
 **/
public class StaticLockTest {

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread1 myThread2 = new MyThread1();
        Thread thread1 = new Thread(myThread1);
        Thread thread2 = new Thread(myThread1);
        thread1.start();
        myThread2.start();
        System.out.println("主线程结束  .....");
    }

}

class MyThread1 extends Thread {
    private StaticLockTest lock = new StaticLockTest();
    private int num;

    @Override
    public void run() {
        synchronized (lock.getClass()) {
            System.out.println("当前线程为：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
                System.out.println(11111111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}