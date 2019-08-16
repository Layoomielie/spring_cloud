package com.example.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
/**
 * @author zhanghongjian
 * @Date 2019/5/10 15:31
 * @Description
 */
@EnableBinding(Sink.class)
public class RecieveService {


    @StreamListener(Sink.INPUT)
    public void recieve(Object payload){
        System.out.println(payload);
    }
}
