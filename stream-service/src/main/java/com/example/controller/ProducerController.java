package com.example.controller;

import com.example.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhanghongjian
 * @Date 2019/5/10 15:23
 * @Description
 */
@RestController
public class ProducerController {

    @Autowired
    private SendService sendService;


    @RequestMapping("/send")
    public String send(String msg) {
        sendService.sendMessage(msg);
        return msg;
    }

    @RequestMapping("/hello")
    public String test(String msg) {
        return msg;

    }

}
