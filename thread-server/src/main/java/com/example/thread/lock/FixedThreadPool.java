package com.example.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：张鸿建
 * @time：2019/11/13 9:49
 * @desc：
 **/
public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Mythread t1 = new Mythread();
        Mythread t2 = new Mythread();
        Mythread t3 = new Mythread();
        Mythread t4 = new Mythread();
        Mythread t5 = new Mythread();
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.shutdown();
    }

}

class Mythread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is runing");
    }
}
