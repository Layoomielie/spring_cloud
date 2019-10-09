package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/10/9 20:48
 * @desc：
 **/
public class PriorityThead extends Thread{
    int count=0;
    @Override
    public void run() {
        while (true){
            count++;
        }
    }

    public int getCount(){
        return this.count;
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityThead t1 = new PriorityThead();
        t1.setPriority(1);
        PriorityThead t2 = new PriorityThead();
        t2.setPriority(10);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.stop();
        t2.stop();
        //因为t2优先级更高 所以执行更快
        System.out.println("t1 count="+t1.getCount());
        System.out.println("t2 count="+t2.getCount());

    }
}
