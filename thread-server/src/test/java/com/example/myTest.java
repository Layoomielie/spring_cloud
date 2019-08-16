package com.example;

import com.example.config.IConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：张鸿建
 * @time：2019/8/8 16:51
 * @desc：
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class myTest {

    @Autowired
    IConfig iConfig;

    @Test
    public void test2s() {
        System.out.println("*********");
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(iConfig.getimap()));
        System.out.println(iConfig.getImap().get("key1"));
        System.out.println("---------------------------");
        System.out.println(IConfig.imap.get("key1"));
    }
}
