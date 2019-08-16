package com.example.rabbitserver.provider;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 13:43
 */

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：张鸿建
 * @time：2019/6/11
 * @desc：
 **/
@Component
public class ApiPaymentSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void order(String msg){
        System.out.println("api.payment.order send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    public void orderQuery(String msg){
        System.out.println("api.payment.order.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    public void orderDetailQuery(String msg){
        System.out.println("api.payment.order.detail.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
    }
}
