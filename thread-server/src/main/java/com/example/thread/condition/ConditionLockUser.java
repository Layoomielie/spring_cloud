package com.example.thread.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：张鸿建
 * @time：2019/11/28 13:33
 * @desc：通过lock.newCondition() 获取锁的通知，当condition调用await()时会释放锁资源
 **/
public class ConditionLockUser {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 拿到锁了");
            System.out.println(Thread.currentThread().getName() + " 等待信号");
            //condition.await(1, TimeUnit.MILLISECONDS);
            condition.await();
            System.out.println(Thread.currentThread().getName() + " 拿到信号");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 拿到锁了");
            Thread.sleep(5000);
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + " 发出信号");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionLockUser conditionLockUser = new ConditionLockUser();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                conditionLockUser.conditionWait();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                conditionLockUser.conditionWait();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                conditionLockUser.conditionSignal();
            }
        });
        pool.shutdown();
    }
}

