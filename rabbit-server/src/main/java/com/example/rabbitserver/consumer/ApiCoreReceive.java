package com.example.rabbitserver.consumer;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 13:40
 */

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author：张鸿建
 * @time：2019/6/11
 * @desc：
 **/
@Component
public class ApiCoreReceive {
    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void user(String msg) {
        System.out.println("api.core receive message: "+msg);
    }
}
