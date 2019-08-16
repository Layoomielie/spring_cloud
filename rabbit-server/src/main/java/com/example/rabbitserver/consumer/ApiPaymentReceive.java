package com.example.rabbitserver.consumer;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 13:41
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
public class ApiPaymentReceive {
    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void order(String msg) {
        System.out.println("api.payment.order receive message: "+msg);
    }
}
