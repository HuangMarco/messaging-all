package com.huangshi.wuji.messaging.rabbitmq.spring.config;

import com.huangshi.wuji.messaging.rabbitmq.spring.constant.SpringRabbitMQConstants;
import com.huangshi.wuji.messaging.rabbitmq.spring.receiver.SpringReceiver;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.huangshi.wuji.messaging.app.biz.service.message.CommonMessageReceiverServiceImpl;

import java.io.IOException;

@Configuration
@Component
@EnableAutoConfiguration
public class SpringRabbitMQConfiguration {

    public static final Logger log = LoggerFactory.getLogger(SpringRabbitMQConfiguration.class);

    @Bean
    Queue queue(){
        return new Queue(SpringRabbitMQConstants.RabbitMQ_Queue_Name, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(SpringRabbitMQConstants.Spring_RabbitMQ_Topic_Exchange_Name);
    }

    /**
     * Binding把queue,exchange,routing key等绑定起来，后面可以直接使用
     * @return
     */
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(SpringRabbitMQConstants.Spring_RabbitMQ_Topic_Exchange_Binding_Root);
    }

    //For Biz queue
    @Bean
    Queue bisQueue(){
        return new Queue(SpringRabbitMQConstants.BIZ_Queue, false);
    }

    //For Biz exchange
    @Bean
    TopicExchange bizExchange(){
        return new TopicExchange(SpringRabbitMQConstants.Biz_Topic_Exchange_Name);
    }

    //For Biz Binding
    @Bean
    public Binding bizBinding(){
        return BindingBuilder.bind(bisQueue()).to(bizExchange()).with(SpringRabbitMQConstants.BIZ_Routing_Key);
    }

    /**
     * 该方法可以直接被注解方式@RabbitListener(queue=xxx)方式替代，注解方式更加灵活
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
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

    /**
     * For Biz
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    /*@Bean
    SimpleMessageListenerContainer bizContainer(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SpringRabbitMQConstants.RabbitMQ_Queue_Name);
        container.setMessageListener(listenerAdapter);
        return container;
    }*/

    /**
     * For Biz
     * @param receiver
     * @return
     */
    /*@Bean
    MessageListenerAdapter bizListenerAdapter(CommonMessageReceiverServiceImpl receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }*/

    /**
     * 配置RabbitTemplate,使用Jackson2JsonMessageConverter
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final CachingConnectionFactory connectionFactory){

        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("============returnedMessage==method=========");
                System.out.println("replyCode: "+replyCode);
                System.out.println("replyText: "+replyText);
                System.out.println("exchange: "+exchange);
                System.out.println("routingKey: "+routingKey);
            }
        });

       /* rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            System.out.println("Return Callback printed.");
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });*/

        // 消息确认，yml需要配置 publisher-confirms: true
/*
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("================");
                System.out.println("correlationData = " + correlationData);
                System.out.println("ack = " + b);
                System.out.println("cause = " + s);
                System.out.println("================");
            }
        });
        */

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("Confirm Callback printed.");
            if (ack) {
                log.info("消息发送到exchange成功,id: {}", correlationData);
            } else {
                log.info("消息发送到exchange失败,原因: {}", cause);
            }
        });


        return rabbitTemplate;
    }

    /**
     * 只影响通过RabbitTemplate发送的消息，自动转换消息为实体类
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
