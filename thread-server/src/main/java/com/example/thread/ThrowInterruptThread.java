package com.example.thread;

import com.example.insecurity.demo2.MyThread;

/**
 * @author：张鸿建
 * @time：2019/7/2 14:12
 * @desc： main线程调用interrupted打断子线程
 **/
public class ThrowInterruptThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 50000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("已经是停止状态了!退出!");
                    throw new InterruptedException();
                }
               // System.out.println("i=" + (i + 1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程打断结束 .. ");
    }
    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            System.out.println(myThread.getName()+" .............." );
            Thread.sleep(100);
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
