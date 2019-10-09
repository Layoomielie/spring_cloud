package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/10/9 20:21
 * @desc：
 **/
public class SuspendThead {

    synchronized public void printLine(){
        System.out.println("进入到 printLine 方法了");
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("a线程被 suspend了 ...");
            Thread.currentThread().suspend();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SuspendThead suspendThead = new SuspendThead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                suspendThead.printLine();
            }
        });
        t1.setName("a");
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("因为printLine被占用了  所以调用不了printLine");
                suspendThead.printLine();
                System.out.println("t2 线程结束");
            }
        });
        t2.start();
    }
}
