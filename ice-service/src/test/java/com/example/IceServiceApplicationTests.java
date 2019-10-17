package com.example;

import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IceServiceApplicationTests {

   /* @Autowired
    RedisTemplateService redisTemplateService;*/

    @Test
    public void redisTest(){

        /*User user = new User();
        user.setId(11);
        user.setUsername("test");
        user.setPassword("hello redis");
        redisTemplateService.set("key1",user);

        User us = redisTemplateService.get("key1",User.class);
        System.out.println(us.getUsername()+":  "+us.getPassword());*/
    }

    @Test
    public void contextLoads() {

    }

}
