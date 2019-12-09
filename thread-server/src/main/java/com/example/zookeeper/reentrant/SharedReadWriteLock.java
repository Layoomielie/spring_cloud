package com.example.zookeeper.reentrant;

import com.example.util.ZookeeperUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author：张鸿建
 * @time：2019/12/9 16:47
 * @desc：可重入读写锁
 **/
public class SharedReadWriteLock {
    private static final String lockPath = "/testZK/sharedreentrantlock";
    private static final Integer clientNums = 5;
    // 共享的资源
    final static FakeLimitedResource resource = new FakeLimitedResource();
    private static CountDownLatch countDownLatch = new CountDownLatch(clientNums);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < clientNums; i++) {
            final String clientName = "client#" + i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework client = ZookeeperUtils.getClient();
                    client.start();
                    final InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);
                    final InterProcessMutex readLock = lock.readLock();
                    final InterProcessMutex writeLock = lock.writeLock();

                    try {
                        // 注意只能先得到写锁再得到读锁，不能反过来！！！
                        if (!writeLock.acquire(10, TimeUnit.SECONDS)) {
                            throw new IllegalStateException(clientName + " 不能得到写锁");
                        }
                        System.out.println(clientName + " 已得到写锁");
                        if (!readLock.acquire(10, TimeUnit.SECONDS)) {
                            throw new IllegalStateException(clientName + " 不能得到读锁");
                        }
                        System.out.println(clientName + " 已得到读锁");
                        try {
                            resource.use(); // 使用资源
                        } finally {
                            System.out.println(clientName + " 释放读写锁");
                            readLock.release();
                            writeLock.release();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        CloseableUtils.closeQuietly(client);
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("结束！");
    }
}
