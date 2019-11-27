package com.example.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author：张鸿建
 * @time：2019/11/8 10:37
 * @desc：
 **/
public class ReadLockThread {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final ReadLockThread test = new ReadLockThread();

        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

    }

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}
