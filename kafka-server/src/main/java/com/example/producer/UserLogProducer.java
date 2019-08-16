package com.example.producer;

import com.alibaba.fastjson.JSON;
import com.example.fallback.KafkaSendResultHandler;
import com.example.log.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhanghongjian
 * @Date 2019/5/6 10:55
 * @Description
 */


@Component
public class UserLogProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler kafkaSendResultHandler;

    public void sendMessage(String topic,String uid) {
        UserLog userLog = new UserLog("ali", uid, "OK");

        kafkaTemplate.send(topic, JSON.toJSONString(userLog));
       // CompletableFuture completable = kafkaTemplate.send(topic, JSON.toJSONString(userLog)).completable();
        /*try {
            Object example = kafkaTemplate.send("example", JSON.toJSONString(userLog)).get();
            System.out.println(example.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //  kafkaTemplate.setMessageConverter(new MessagingMessageConverter());
    }

}
