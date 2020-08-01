package com.huangshi.wuji.messaging.rabbitmq.spring.receiver;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 纯POJO，定义接收消息方法
 */

@Component
public class SpringReceiver {

    //倒计时门栓
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }


}
