package com.example.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author zhanghongjian
 * @Date 2019/5/11 11:28
 * @Description
 */
@EnableBinding(Processor.class)
public class TransFormService {


    @ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public Object transform(Object payload){
        System.out.println("消息中转站："+payload);
        return payload;
    }
}
