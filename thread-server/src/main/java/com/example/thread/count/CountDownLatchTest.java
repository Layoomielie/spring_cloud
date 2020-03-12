package com.example.thread.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：张鸿建
 * @time：2019/11/27 10:47
 * @desc：
 **/
public class CountDownLatchTest implements Runnable {
    CountDownLatch latch;
    ReentrantLock lock;
    Condition condition;

    public CountDownLatchTest(CountDownLatch latch, ReentrantLock lock, Condition condition) {
        this.latch = latch;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 拿到锁了");
            System.out.println(Thread.currentThread().getName() + " 正在等待释放");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " notify接收 等待2s 可以释放");
            Thread.sleep(2000);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void conditionSignal() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 拿到锁了");
            Thread.sleep(5000);
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + " 发出信号");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest(latch, reentrantLock, condition);

        executorService.submit(countDownLatchTest);
        executorService.submit(countDownLatchTest);

        System.out.println("主线程 2S后 释放notify信号");
        Thread.sleep(2000);
        countDownLatchTest.conditionSignal();

        latch.await();
        System.out.println("子线程全部完成 子线程1s后进行释放");
        Thread.sleep(1000);
        executorService.shutdown();

    }

}
