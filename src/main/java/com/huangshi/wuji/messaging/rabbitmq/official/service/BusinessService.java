package com.huangshi.wuji.messaging.rabbitmq.official.service;

import com.huangshi.wuji.messaging.rabbitmq.official.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    RabbitMQProducer producer;

    public void doBusiness(String message)throws Exception{
        producer.sendMessage(message);
    }
}
