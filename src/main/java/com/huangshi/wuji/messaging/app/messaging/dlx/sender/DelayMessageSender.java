package com.huangshi.wuji.messaging.app.messaging.dlx.sender;

import com.huangshi.wuji.messaging.app.messaging.dlx.config.RabbitMQConfigForTTLDLX;
import com.huangshi.wuji.messaging.app.messaging.dlx.constants.DelayTypeEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, DelayTypeEnum type){
        switch (type){
            case DELAY_10s:
                rabbitTemplate.convertAndSend(RabbitMQConfigForTTLDLX.DELAY_EXCHANGE_NAME, RabbitMQConfigForTTLDLX.DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case DELAY_60s:
                rabbitTemplate.convertAndSend(RabbitMQConfigForTTLDLX.DELAY_EXCHANGE_NAME, RabbitMQConfigForTTLDLX.DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
        }
    }
}
