package com.example.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @author：张鸿建
 * @time：2019/7/27 13:55
 * @desc：
 **/
public class MyKafkaTemplate  extends KafkaTemplate {
    public MyKafkaTemplate(ProducerFactory producerFactory) {
        super(producerFactory);
    }

    public MyKafkaTemplate(ProducerFactory producerFactory, boolean autoFlush) {
        super(producerFactory, autoFlush);
    }
}
