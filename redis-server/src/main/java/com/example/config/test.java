package com.example.config;

import redis.clients.jedis.Jedis;

/**
 * @author：张鸿建
 * @time：2019/12/10 9:25
 * @desc：
 **/
public class test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.auth("foobared");
        System.out.println("连接成功");
        //查看服务是否运行

        System.out.println("服务正在运行: "+jedis.ping());
    }
}
