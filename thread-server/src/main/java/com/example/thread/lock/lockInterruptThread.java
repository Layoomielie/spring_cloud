package com.example.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：张鸿建
 * @time：2019/11/8 10:16
 * @desc：
 **/
public class lockInterruptThread {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        lockInterruptThread test = new lockInterruptThread();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException{
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            Thread.sleep(2000);
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
}

class MyThread extends Thread {
    private lockInterruptThread test = null;
    public MyThread(lockInterruptThread test) {
        this.test = test;
    }
    @Override
    public void run() {

        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}
