package com.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：张鸿建
 * @time：2019/10/9 20:38
 * @desc： yeild
 **/
public class YieldThread extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        int count=0;
        for (int i = 0; i <500 ; i++) {
            // 使用yield会释放cpu资源
            Thread.yield();
            count++;

            //System.out.println(Thread.currentThread().getName()+" : "+i);
        }
        System.out.println(Thread.currentThread().getName()+" :"+(System.currentTimeMillis()-begin));
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        YieldThread yieldThread1 = new YieldThread();
        YieldThread yieldThread2 = new YieldThread();
        executorService.submit(yieldThread1);
        yieldThread1.join();

        executorService.submit(yieldThread2);
        executorService.shutdown();
    }
}
