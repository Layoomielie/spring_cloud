package com.example.exampleIml;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：张鸿建
 * @time：2019/10/14 15:11
 * @desc：
 **/
public class Station extends Thread {
    private int ticket = 40;
    private Object obj = "aa";
    private Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (ticket > 0) {
            lock.lock();
            try {
                if (ticket > 0) {
                    System.out.println("当前" + Thread.currentThread().getName() + "正在售出第" + (40 - ticket + 1) + "张票");
                } else {
                    System.out.println(Thread.currentThread().getName() + ":票已售完");
                }
                ticket--;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        Station station = new Station();
        Thread thread1 = new Thread(station);
        Thread thread2 = new Thread(station);
        Thread thread3 = new Thread(station);
        thread1.setName("车站1");
        thread2.setName("车站2");
        thread3.setName("车站3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
