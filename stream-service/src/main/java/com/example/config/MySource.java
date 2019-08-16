package com.example.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhanghongjian
 * @Date 2019/5/11 9:58
 * @Description
 */

public interface MySource {
    // 这个需要定义接收到的消费端
    @Output("myOutput")
    MessageChannel myOutput();


}
