package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/10/9 20:55
 * @desc：守护线程
 **/
public class DaeMonThead extends Thread{
    private int a=0;

    @Override
    public void run() {
        while (true){
        System.out.println(a++);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaeMonThead daeMonThead = new DaeMonThead();
        // 设置为守护线程后  main结束 子线程会结束
        daeMonThead.setDaemon(true);
        daeMonThead.start();
        Thread.sleep(3000);
        System.out.println("main线程结束");
    }
}
