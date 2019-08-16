package com.example.rabbitserver.provider;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 11:14
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
public class PaymentNotifySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sender(String msg){
        System.out.println(msg);
        rabbitTemplate.convertAndSend("notify.payment", msg);
    }
}
