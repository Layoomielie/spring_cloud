package com.example.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：张鸿建
 * @time：2019/11/26 15:42
 * @desc： 字节码文件锁
 **/
public class StaticLockThread extends Thread {

    private Object lock=new Object();

    @Override
    public void run() {
        synchronized (lock.getClass()) {
            System.out.println("当前线程为：" + Thread.currentThread().getName() + " 获取到锁");
            try {
                Thread.sleep(2000);
                System.out.println("当前线程为：" + Thread.currentThread().getName() + " 释放了锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        StaticLockThread myThread = new StaticLockThread();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(myThread);
        executorService.submit(myThread);
        System.out.println("主线程结束  .....");
        executorService.shutdown();
    }

}

