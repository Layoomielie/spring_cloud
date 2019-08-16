package com.example.fallback;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.messaging.Message;

import java.lang.reflect.Type;

/**
 * @author：张鸿建
 * @time：2019/7/26 20:19
 * @desc：
 **/

public class MyRecordMessageConverter implements RecordMessageConverter {
    Logger logger = LoggerFactory.getLogger(MyRecordMessageConverter.class);
    @Override
    public Message<?> toMessage(ConsumerRecord<?, ?> consumerRecord, Acknowledgment acknowledgment, Consumer<?, ?> consumer, Type type) {
        logger.info("MyRecordMessageConverter  ...");
        logger.info("consumerRecord: ",consumerRecord);
        logger.info("Acknowledgment: ",acknowledgment);
        logger.info("Consumer: ",consumer);
        logger.info("Type: ",type);
        return null;
    }

    @Override
    public ProducerRecord<?, ?> fromMessage(Message<?> message, String s) {
        logger.info("ProducerRecord  ...");
        logger.info("Consumer: ",message);
        logger.info("s: ",s);
        return null;
    }
}
