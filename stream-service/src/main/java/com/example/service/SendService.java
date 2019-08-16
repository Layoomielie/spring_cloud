package com.example.service;

import com.example.config.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author zhanghongjian
 * @Date 2019/5/10 15:21
 * @Description
 */
@EnableBinding(MySource.class)
public class SendService {

    @Autowired
    MySource mySource;

    public void sendMessage(String  msg) {
        mySource.myOutput().send(MessageBuilder.withPayload(msg).build());
    }
}
