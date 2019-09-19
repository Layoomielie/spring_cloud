package com.kafka;

import com.alibaba.fastjson.JSON;
import com.kafka.fallback.KafkaSendResultHandler;
import com.kafka.message.BaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/*
 * @author: zhanghongjian
 * @date: 2019年5月8日
 * @descript:
 */
@Component
public class KafKaUtil {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler kafkaSendResultHandler;

    public void sendMessage(String topic, BaseMessage baseMessage) {
        kafkaTemplate.send(topic, JSON.toJSONString(baseMessage)).addCallback(kafkaSendResultHandler);
    }
}
