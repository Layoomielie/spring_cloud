package com.example.thread.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：张鸿建
 * @time：2019/11/27 10:47
 * @desc：
 **/
public class countDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行。。。");
        ExecutorService ex1 = Executors.newSingleThreadExecutor();
        ex1.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(200);
                    System.out.println("子线程: "+Thread.currentThread().getName()+" 执行");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        ex1.shutdown();
        ExecutorService ex2 = Executors.newSingleThreadExecutor();
        ex2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程："+Thread.currentThread().getName()+" 执行");
                latch.countDown();
            }
        });
        ex2.shutdown();
        System.out.println("等待两个线程执行完毕 ");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个线程都执行完毕");
    }

}
