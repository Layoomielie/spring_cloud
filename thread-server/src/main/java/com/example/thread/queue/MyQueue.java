package com.example.thread.queue;

import org.omg.CORBA.OBJ_ADAPTER;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author：张鸿建
 * @time：2019/11/28 10:40
 * @desc：
 **/
public class MyQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        Put put = new Put(queue);
        Take take = new Take(queue);
        put.start();
        take.start();
    }
}

class Put extends Thread {
    LinkedBlockingQueue<Object> queue;

    public Put(LinkedBlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < 2000; i++) {
                    queue.offer("AAAA:" + i);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Take extends Thread {
    LinkedBlockingQueue<Object> queue;

    public Take(LinkedBlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object take = queue.peek();
                System.out.println(take);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}