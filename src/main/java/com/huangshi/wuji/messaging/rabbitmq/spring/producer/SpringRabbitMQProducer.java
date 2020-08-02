package com.huangshi.wuji.messaging.rabbitmq.spring.producer;

import com.huangshi.wuji.messaging.rabbitmq.spring.constant.SpringRabbitMQConstants;
import com.huangshi.wuji.messaging.rabbitmq.spring.receiver.SpringReceiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SpringRabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SpringReceiver receiver;

    public void sendMessage(String message)throws Exception{
        rabbitTemplate.convertAndSend(SpringRabbitMQConstants.Spring_RabbitMQ_Topic_Exchange_Name, SpringRabbitMQConstants.RabbitMQ_Message_Routing_Key, message);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
