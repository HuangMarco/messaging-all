package com.huangshi.wuji.messaging.rabbitmq.spring.config;

import com.huangshi.wuji.messaging.rabbitmq.spring.constant.SpringRabbitMQConstants;
import com.huangshi.wuji.messaging.rabbitmq.spring.receiver.SpringReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringRabbitMQConfiguration {

    @Bean
    Queue queue(){
        return new Queue(SpringRabbitMQConstants.RabbitMQ_Queue_Name, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(SpringRabbitMQConstants.Spring_RabbitMQ_Topic_Exchange_Name);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SpringRabbitMQConstants.RabbitMQ_Queue_Name);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(SpringReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
