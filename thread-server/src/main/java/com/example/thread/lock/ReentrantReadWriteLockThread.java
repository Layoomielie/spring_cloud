package com.example.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author：张鸿建
 * @time：2019/11/12 15:59
 * @desc：
 **/
public class ReentrantReadWriteLockThread {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private int flag=0;
    public static void main(String[] args) {
        final ReentrantReadWriteLockThread test = new ReentrantReadWriteLockThread();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }
            ;
        }.start();
        new Thread() {
            public void run() {
                test.write(Thread.currentThread());
                System.out.println(test.flag);
            }
            ;
        }.start();
    }

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(thread.getName() + "正在进行读操作");
                flag++;
            }
            System.out.println(thread.getName() + "读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void write(Thread thread) {
        rwl.writeLock().lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(thread.getName() + "正在进行写操作");
                flag++;
            }
            System.out.println(thread.getName() + "写操作完毕");
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
