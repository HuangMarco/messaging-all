package com.huangshi.wuji.messaging.rabbitmq.official.receiver;

import com.huangshi.wuji.messaging.rabbitmq.official.constants.RabbitMQConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    @EventListener(ApplicationReadyEvent.class)
    public void reciveMessage()throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.17");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitMQConstants.Queue_Name, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(RabbitMQConstants.Queue_Name, true, deliverCallback, consumerTag -> { });
    }
}
