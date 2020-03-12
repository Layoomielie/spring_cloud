package com.example.thread.count;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author：张鸿建
 * @time：2019/11/27 16:38
 * @desc：cyclicBarrier屏障  等待其他线程写入
 **/

public class CyclicBarrierTest extends Thread{
    private CyclicBarrier cyclicBarrier;
    public CyclicBarrierTest(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 正在写入数据...");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" 写入数据完毕，等待其他线程写入...");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" 线程写入完毕，继续执行其他任务");
    }
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println(111111111);
            }
        });
        for(int i=0;i<4;i++) {
            new CyclicBarrierTest(cyclicBarrier).start();
        }
    }
}