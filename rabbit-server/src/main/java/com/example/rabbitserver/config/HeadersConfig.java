package com.example.rabbitserver.config;/**
 * ${tag}
 *
 * @author zhanghongjian
 * @Date 2019/6/11 15:15
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：张鸿建
 * @time：2019/6/11
 * @desc：
 **/
@Configuration
public class HeadersConfig {
    @Bean
    public Queue creditBankQueue() {
        return new Queue("credit.bank");
    }

    @Bean
    public Queue creditFinanceQueue() {
        return new Queue("credit.finance");
    }

    @Bean
    public HeadersExchange creditBankExchange() {
        return new HeadersExchange("creditBankExchange");
    }

    @Bean
    public HeadersExchange creditFinanceExchange() {
        return new HeadersExchange("creditFinanceExchange");
    }

    @Bean
    public Binding bindingCreditAExchange(Queue creditBankQueue, HeadersExchange creditBankExchange) {
        Map<String,Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerValues).match();
    }

    @Bean
    public Binding bindingCreditBExchange(Queue creditFinanceQueue, HeadersExchange creditFinanceExchange) {
        Map<String,Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headerValues).match();
    }
}
