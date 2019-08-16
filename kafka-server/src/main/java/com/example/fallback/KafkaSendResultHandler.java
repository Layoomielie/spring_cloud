package com.example.fallback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author：张鸿建
 * @time：2019/7/26 16:15
 * @desc：
 **/
@Component
public class KafkaSendResultHandler implements ListenableFutureCallback<SendResult> {

    @Override
    public void onFailure(Throwable throwable) {
        System.out.println(".... error ...");
        System.out.println(throwable);
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();
        System.out.println("..... sucesss ... ");
        System.out.println(recordMetadata.topic());
        System.out.println(" ---------------------------------- ");
        Object value = sendResult.getProducerRecord().value();
        JSONObject jsonObject = JSON.parseObject(value.toString());
        System.out.println(jsonObject);
    }

    /*@Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {

        System.out.println("..... ProducerRecord ... ");
        System.out.println(producerRecord.toString());
        System.out.println("..... RecordMetadata ... ");
        System.out.println(recordMetadata.toString());
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        System.out.println("..... sucesss ... ");
        System.out.println("..... ProducerRecord ... ");
        System.out.println(producerRecord.toString());
        System.out.println("..... RecordMetadata ... ");
        System.out.println(exception);
    }*/
}
