package com.example.rabbitserver.consumer;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 15:16
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
public class ApiCreditReceive {

    @RabbitHandler
    @RabbitListener(queues = "credit.bank")
    public void creditBank(String msg) {
        System.out.println("credit.bank receive message: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "credit.finance")
    public void creditFinance(String msg) {
        System.out.println("credit.finance receive message: "+msg);
    }
}
