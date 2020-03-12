package com.example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：张鸿建
 * @time：2020/3/9 14:25
 * @desc：
 **/
public class SecKillDemo implements Runnable{
    private Jedis jedis = new Jedis("47.103.17.23", 6379);

    private String customerName;

    private String key;

    private ReentrantLock reentrantLock;

    public SecKillDemo(String customerName, String key,ReentrantLock reentrantLock) {
        this.customerName = customerName;
        this.key = key;
        this.reentrantLock=reentrantLock;
    }

    @Override
    public void run() {
        //通过watch实现redis的incr(原子递增操作)
        jedis.watch(key);
        boolean success = false;
        String data;
        int currentNum;
        while (!success) {//可重复抢购直到成功
            data = jedis.get(key);
            currentNum = Integer.parseInt(data);
            if (currentNum > 0) {
                //开启事务
                Transaction transaction = jedis.multi();
                //设置新值,如果key的值被其它连接的客户端修改，那么当前连接的exec命令将执行失败
                currentNum--;
                transaction.set(key, String.valueOf(currentNum));

                List res = transaction.exec();
                if (res.size() == 0) {
                    System.out.println(customerName + " 抢购失败");
                    success = false;
                } else {
                    success = true;
                    System.out.println(customerName + " 抢购成功,[" + key + "]剩余：" + currentNum);
                }
            } else {
                System.out.println("商品售空,活动结束!");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantLock reentrantLock = new ReentrantLock();
        SecKillDemo secKillDemo = new SecKillDemo("草莓","caomei",reentrantLock);
        for (int i=0;i<600;i++) {
            executorService.submit(secKillDemo);
        }

    }
}
