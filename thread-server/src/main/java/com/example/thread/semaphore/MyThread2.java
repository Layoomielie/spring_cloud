package com.example.thread.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author：张鸿建
 * @time：2019/11/28 9:25
 * @desc： SemaphoreService 控制并发线程数量
 **/
public class MyThread2 extends Thread {
    private SemaphoreService service;
    private CountDownLatch latch;
    private CyclicBarrier cyclicBarrier;
    public MyThread2(SemaphoreService service,CountDownLatch latch,CyclicBarrier cyclicBarrier) {
        this.service = service;
        this.latch=latch;
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {
        super.run();
        this.service.doSomething();
        //latch.countDown();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class SemaphoreService {
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private Semaphore semaphore = new Semaphore(6,true);// 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码  true 为实现公平锁


    public void doSomething() {
        try {
            /**
             * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
             * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
             * */
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName() + ":doSomething start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":doSomething end");
            semaphore.release(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public int availablePermits() {    // 查看可用通路数
        return semaphore.availablePermits();
    }

    public static void main(String[] args) throws InterruptedException {
        //CountDownLatch countDownLatch = new CountDownLatch(10);
        SemaphoreService semaphoreService = new SemaphoreService();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("当前最后通道 ："+semaphoreService.availablePermits());
            }
        });
        for (int i = 0; i < 10; i++) {
            MyThread2 myThread2 = new MyThread2(semaphoreService,null,cyclicBarrier);
            myThread2.start();
            System.out.println("当前剩余通道数： "+semaphoreService.availablePermits());
        }
        //countDownLatch.await();
        //System.out.println("最后剩余通道数： "+semaphoreService.availablePermits());
    }
}