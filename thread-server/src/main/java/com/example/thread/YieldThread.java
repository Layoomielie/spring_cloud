package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/10/9 20:38
 * @desc：
 **/
public class YieldThread extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        int count=0;
        for (int i = 0; i <5000000 ; i++) {
            // 使用yield会释放cpu资源
            //Thread.yield();
            count++;
        }
        System.out.println(System.currentTimeMillis()-begin);
    }

    public static void main(String[] args) {
        new YieldThread().start();
    }
}
