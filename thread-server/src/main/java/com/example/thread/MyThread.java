package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/7/2 14:12
 * @desc：
 **/
public class MyThread extends Thread {
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
        System.out.println("jieshu .. ");
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
