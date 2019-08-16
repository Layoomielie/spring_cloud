package com.example.controller;

import com.example.producer.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghongjian
 * @Date 2019/5/8 16:39
 * @Description
 */
@RestController
public class HelloController {

    @Autowired
    UserLogProducer userLogProducer;

    @RequestMapping("/kafka/send")
    public void sendMessage() {
        for(int i=0;i<2;i++) {
            userLogProducer.sendMessage("example",i+"");
        }
    }

    @RequestMapping("/kafka/Error/send")
    public void sendErrorMessage() {
        for(int i=0;i<2;i++) {
            userLogProducer.sendMessage("error",i+"");
        }
    }
}
