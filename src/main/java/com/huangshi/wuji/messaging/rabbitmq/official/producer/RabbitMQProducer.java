package com.huangshi.wuji.messaging.rabbitmq.official.producer;

import com.huangshi.wuji.messaging.annotation.MessageLog;
import com.huangshi.wuji.messaging.rabbitmq.official.constants.RabbitMQConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMQProducer {

    @MessageLog(value="test")
    public void sendMessage(String message) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.17");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RabbitMQConstants.Queue_Name, false, false, false, null);
//            String message = "Hello World!";
            channel.basicPublish("", RabbitMQConstants.Queue_Name, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
