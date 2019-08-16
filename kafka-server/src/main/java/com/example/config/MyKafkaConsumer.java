package com.example.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.Properties;

/**
 * @author：张鸿建
 * @time：2019/7/27 15:32
 * @desc：
 **/
public class MyKafkaConsumer extends KafkaConsumer {
    public MyKafkaConsumer(Map configs) {
        super(configs);
    }

    public MyKafkaConsumer(Map configs, Deserializer keyDeserializer, Deserializer valueDeserializer) {
        super(configs, keyDeserializer, valueDeserializer);
    }

    public MyKafkaConsumer(Properties properties) {
        super(properties);
    }

    public MyKafkaConsumer(Properties properties, Deserializer keyDeserializer, Deserializer valueDeserializer) {
        super(properties, keyDeserializer, valueDeserializer);
    }
}
