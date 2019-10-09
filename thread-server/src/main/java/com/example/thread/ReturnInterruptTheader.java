package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/10/9 20:08
 * @desc：
 **/
public class ReturnInterruptTheader extends Thread{

    @Override
    public void run() {
        while (true){
            if(this.isInterrupted()){
                System.out.println("线程停止。。。");
                return;
            }
            System.out.println("timer="+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReturnInterruptTheader returnInterruptTheader = new ReturnInterruptTheader();
        returnInterruptTheader.start();
        Thread.sleep(1000);
        returnInterruptTheader.interrupt();
    }
}
