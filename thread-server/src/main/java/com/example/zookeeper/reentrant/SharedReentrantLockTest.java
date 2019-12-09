package com.example.zookeeper.reentrant;

import com.example.util.ZookeeperUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.utils.CloseableUtils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author：张鸿建
 * @time：2019/12/9 16:22
 * @desc： zookeeper可重入锁
 **/
public class SharedReentrantLockTest {
    private static final String lockPath = "/testZK/sharedreentrantlock";
    private static final Integer clientNums = 5;
    // 共享的资源
    final static FakeLimitedResource resource = new FakeLimitedResource();
    private static CountDownLatch countDownLatch = new CountDownLatch(clientNums);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < clientNums; i++) {
            String clientName = "client#" + i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework client = ZookeeperUtils.getClient();
                    client.start();
                    Random random = new Random();
                    try {

                        final InterProcessMutex lock = new InterProcessMutex(client, lockPath);
                        // 每个客户端请求10次共享资源
                        for (int j = 0; j < 10; j++) {
                            //这里获取锁  如果获取不到会在这里等待
                            if (!lock.acquire(10, TimeUnit.SECONDS)) {
                                throw new IllegalStateException(j + ". " + clientName + " 不能得到互斥锁");
                            }
                            try {
                                System.out.println(j + ". " + clientName + " 已获取到互斥锁");
                                resource.use(); // 使用资源
                                // 再次获取锁
                                if (!lock.acquire(10, TimeUnit.SECONDS)) {
                                    throw new IllegalStateException(j + ". " + clientName + " 不能再次得到互斥锁");
                                }
                                System.out.println(j + ". " + clientName + " 已再次获取到互斥锁");
                                lock.release(); // 申请几次锁就要释放几次锁
                            } finally {
                                System.out.println(j + ". " + clientName + " 释放互斥锁");
                                lock.release(); // 总是在finally中释放
                            }
                            Thread.sleep(random.nextInt(100));
                        }
                    } catch (Throwable e) {
                        System.out.println(e.getMessage());
                    } finally {
                        CloseableUtils.closeQuietly(client);
                        System.out.println(clientName + " 客户端关闭！");
                        countDownLatch.countDown();
                    }
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("结束！");
    }
}