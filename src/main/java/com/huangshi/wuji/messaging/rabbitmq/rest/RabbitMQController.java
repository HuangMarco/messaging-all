package com.huangshi.wuji.messaging.rabbitmq.rest;

import com.huangshi.wuji.messaging.rabbitmq.official.producer.RabbitMQProducer;
import com.huangshi.wuji.messaging.rabbitmq.official.service.BusinessService;
import com.huangshi.wuji.messaging.swagger.config.SwaggerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = SwaggerConstants.API_RabbitMQ)
public class RabbitMQController {


    @Autowired
    BusinessService service;

    @RequestMapping(value = "/send/test",method = RequestMethod.GET)
    public void sendMessageTest()throws Exception{

        String firstMessage = "hello! are you ok?";

        service.doBusiness(firstMessage);


    }
}
