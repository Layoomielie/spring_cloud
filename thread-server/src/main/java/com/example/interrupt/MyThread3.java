package com.example.interrupt;

/**
 * @author：张鸿建
 * @time：2019/8/13 20:21
 * @desc：基础线程
 **/
public class MyThread3 extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 50000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("当前线程已经结束 后面的不再执行");
                    throw new InterruptedException();
                }
                System.out.println("i = " + i);
            }
        } catch (InterruptedException e) {
            System.out.println("抛出异常  然后停止 ....");
            e.printStackTrace();
        }

    }
}
