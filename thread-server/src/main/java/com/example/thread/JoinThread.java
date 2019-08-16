package com.example.thread;

/**
 * @author：张鸿建
 * @time：2019/7/2 14:23
 * @desc：
 **/
public class JoinThread extends Thread {
    private Thread thread;

    public JoinThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            thread.join();
            for (int i = 0; i < 10; i++) {
                System.out.println(thread.getName() + "的执行 " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 1; i <= 10; i++) {
            Thread curThread = new JoinThread(previousThread);
            curThread.start();
            previousThread = curThread;
        }

    }
}
