package com.huangshi.wuji.messaging.rabbitmq.spring.constant;

public class SpringRabbitMQConstants {

    private SpringRabbitMQConstants() {}

    public static final String RabbitMQ_Queue_Name = "spring-hs-rabbitmq-queue";
    public static final String Spring_RabbitMQ_Topic_Exchange_Name = "spring-hs-rabbitmq-exchange";

    public static final String Spring_RabbitMQ_Topic_Exchange_Binding_Root = "spring.rabbimq.hs.#";
    public static final String Spring_RabbitMQ_Topic_Exchange_Binding_Test = "spring.rabbimq.hs.test";
    public static final String RabbitMQ_Message_Routing_Key = "hs.first.route.key";



    //For Biz
    public static final String Biz_Topic_Exchange_Name = "biz-exchange";
    public static final String Message_BIZ_Routing_Key = "biz.route.key";
    public static final String Message_BIZ_Queue = "biz-msg-queue";


}
