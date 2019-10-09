package com.example.test;

public class LockObj {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread, "线程1");
        Thread t2 = new Thread(myThread, "线程2");
        t1.start();
        t2.start();
    }
}

class MyThread extends  Thread{

    private int ticket=100;
    private Object lock=new Object();

    @Override
    public void run() {
        while (ticket>0){
            try {
                Thread.sleep(100);
                sale();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }


        }
    }

    public void sale(){
        synchronized (lock){
            System.out.println("当前线程为："+Thread.currentThread().getName()+" 还剩下："+ticket+"张票");
            ticket--;
        }

    }


}
