package com.example.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：张鸿建
 * @time：2019/11/12 15:47
 * @desc：
 **/
public class ReentrantLockThread {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        ReentrantLockThread test = new ReentrantLockThread();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
                System.out.println(test.arrayList.toString());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
                System.out.println(test.arrayList.toString());
            };
        }.start();
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
