package com.example.thread;

import java.util.concurrent.*;

/**
 * @author：张鸿建
 * @time：2019/7/1 15:22
 * @desc： callable线程 有返回值
 **/
public class CallableThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 10000; i++) {
            //System.out.println(Thread.currentThread().getName() + "的循环变量i的值：" + i);
        }
        return i;
    }

    public static void main(String[] args) {
        //使用lambda表达式
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CallableThread callableThread = new CallableThread();

        Future<Integer> submit = executorService.submit(callableThread);


        try {
            System.out.println("线程的返回值：" + submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
