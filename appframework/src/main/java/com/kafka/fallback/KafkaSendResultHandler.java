package com.kafka.fallback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author：张鸿建
 * @time：2019/7/27 14:02
 * @desc： 配置kafka监听回调
 **/
@SuppressWarnings("rawtypes")
@Component
public class KafkaSendResultHandler implements ListenableFutureCallback<SendResult> {

    Logger logger = LoggerFactory.getLogger(KafkaSendResultHandler.class);


    public void onFailure(Throwable throwable) {
        logger.error("kafka send message fail : ", throwable);
    }


    public void onSuccess(SendResult result) {
        JSONObject jsonObject = JSON.parseObject(result.getProducerRecord().value().toString());
        logger.debug("kafka send message success : ", jsonObject);
    }
}
