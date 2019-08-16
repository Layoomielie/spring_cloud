package com.example.rabbitserver.consumer;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 11:13
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
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceive {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println(msg);
    }
}
