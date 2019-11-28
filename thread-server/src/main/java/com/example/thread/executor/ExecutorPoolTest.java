package com.example.thread.executor;

import java.util.concurrent.*;

/**
 * @author：张鸿建
 * @time：2019/11/28 14:02
 * @desc：
 **/
public class ExecutorPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Executors.newCachedThreadPool();
        ExecutorThread executorThread = new ExecutorThread();
        ExecutorCallable executorCallable = new ExecutorCallable();
        Future<?> submit1 = pool.submit(executorCallable);
        Future<?> submit2 = pool.submit(executorCallable);
        Future<?> submit3 = pool.submit(executorCallable);
        System.out.println(submit1.get());
        System.out.println(submit2.get());
        System.out.println(submit3.get());
        pool.shutdown();
    }
}

class ExecutorThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在运行");
    }
}

class ExecutorCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("正在运行");
        Thread.sleep(1000);
        return "AAAAAAAAAAAAA";
    }
}