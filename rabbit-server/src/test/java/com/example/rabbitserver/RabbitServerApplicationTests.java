package com.example.rabbitserver;

import com.example.rabbitserver.provider.PaymentNotifySender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitServerApplicationTests {
    @Autowired
    private PaymentNotifySender sender;

    @Test
    public void contextLoads() {
        sender.sender("支付订单号："+System.currentTimeMillis());
    }

}
