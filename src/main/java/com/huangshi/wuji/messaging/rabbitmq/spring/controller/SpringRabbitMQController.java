package com.huangshi.wuji.messaging.rabbitmq.spring.controller;

import com.huangshi.wuji.messaging.rabbitmq.spring.producer.SpringRabbitMQProducer;
import com.huangshi.wuji.messaging.swagger.config.SwaggerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = SwaggerConstants.API_RabbitMQ)
public class SpringRabbitMQController {

    @Autowired
    SpringRabbitMQProducer producer;

    @RequestMapping(value = "/spring/send/message",method = RequestMethod.POST)
    public void sendMessage(String message)throws Exception{
        producer.sendMessage(message);
    }
}
