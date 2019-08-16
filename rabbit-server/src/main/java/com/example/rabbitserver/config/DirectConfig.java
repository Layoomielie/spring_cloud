package com.example.rabbitserver.config;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 11:11
 */

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：张鸿建
 * @time：2019/6/11
 * @desc：
 **/
@Configuration
public class DirectConfig {

    @Bean
    public Queue paymentNotifyQueue() {
        return new Queue("notify.payment");
    }
}
