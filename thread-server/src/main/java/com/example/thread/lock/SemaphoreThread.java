package com.example.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：张鸿建
 * @time：2020/3/12 15:57
 * @desc： 信号量测试   信号量比synchronized有更细的粒度
 **/
public class SemaphoreThread {
    private static int LatchNum=30;

    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private final static Semaphore semaphore = new Semaphore(20);
    private final static CountDownLatch countDownLatch = new CountDownLatch(LatchNum);
    private static AtomicInteger count=new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <LatchNum ; i++) {
        executorService.execute(()->{
            try {
                //semaphore.acquire(2);
                addCount();
                //semaphore.release(2);

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        });
        }
        countDownLatch.await();
        System.out.println("所有线程 执行完毕 最终a值为："+count);
        executorService.shutdown();
    }


    public static void addCount(){
        for (int i = 0; i < 100; i++) {
            count.getAndIncrement();
        }
    }
}
