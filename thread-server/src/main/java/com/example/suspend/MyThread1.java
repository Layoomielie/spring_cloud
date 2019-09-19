package com.example.suspend;

/**
 * @author：张鸿建
 * @time：2019/8/14 11:04
 * @desc：
 **/
public class MyThread1 extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {

        while (true) {
            i++;
        }
    }
}
