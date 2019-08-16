package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author zhanghongjian
 * @Date 2019/5/6 11:05
 * @Description
 */

@Component
@Slf4j
public class UserLogConsumer {


    Logger log = LoggerFactory.getLogger(UserLogConsumer.class);



    @KafkaListener(topics = {"example"})
    public  void consumer(ConsumerRecord<?, ?> consumerRecord){

        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        long offset = consumerRecord.offset();
        int partition = consumerRecord.partition();
        log.info("offset   >>>>>>>>>>>>>>>>>>>>   "+ offset);
        log.info("partition   >>>>>>>>>>>>>>>>>>>>   "+ partition);
        log.info("recode   >>>>>>>>>>>>>>>>>>>>   "+ optional);
        //KafkaConsumer consumer = new KafkaConsumer<>();
        // 手动同步提交
        //consumer.commitSync();

        if(optional.isPresent()){
           Object object= optional.get();
            System.out.println("消息为   >>>>>>>>>>>>>   "+object.toString());

        }
    }
    @KafkaListener(topics = {"error"})
    public  void Errorconsumer(ConsumerRecord<?,?> consumerRecord){
            throw new RuntimeException("抛出一个自定义异常 。。。。。");
    }
}
