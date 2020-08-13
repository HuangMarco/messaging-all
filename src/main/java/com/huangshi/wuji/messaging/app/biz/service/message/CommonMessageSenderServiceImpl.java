package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.OperationEntityDTO;
import com.huangshi.wuji.messaging.app.biz.msgcontent.BizRelatedMessage;
import com.huangshi.wuji.messaging.app.messaging.repository.MessagingEntityRepository;
import com.huangshi.wuji.messaging.rabbitmq.spring.constant.SpringRabbitMQConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CommonMessageSenderServiceImpl implements CommonMessageSenderService {

    /**
     * Message的配置见：com.huangshi.wuji.messaging.rabbitmq.spring.config.SpringRabbitMQConfiguration
     */

    private void sendMessageViaClient()throws IOException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.3.17");
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
//        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.createConnection();

        Channel channel = connection.createChannel(true);
        String exchangeType = "topic";


        channel.exchangeDeclare(SpringRabbitMQConstants.Biz_Topic_Exchange_Name, exchangeType, true, false, null);
        channel.queueDeclare(SpringRabbitMQConstants.BIZ_Queue, false, false, false, null);
        channel.queueBind(SpringRabbitMQConstants.BIZ_Queue, SpringRabbitMQConstants.Biz_Topic_Exchange_Name, SpringRabbitMQConstants.BIZ_Routing_Key);
        channel.confirmSelect();

        String msg = "Send Msg By Confirm ...";
        channel.basicPublish(SpringRabbitMQConstants.Biz_Topic_Exchange_Name, SpringRabbitMQConstants.BIZ_Routing_Key, null, msg.getBytes());

        // 6. 设置监听
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("-------ACK Success--------");
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("-------ACK Failed--------");
            }
        });
    }

    @Autowired
    private MessagingEntityRepository messageRepo;

    private static final Logger log = LoggerFactory.getLogger(CommonMessageSenderServiceImpl.class);

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public CommonMessageSenderServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @Scheduled(fixedDelay = 3000L)
    @Override
    @RabbitListener(queues = SpringRabbitMQConstants.BIZ_Queue)
    public void sendBizMessage(BizMessageEntityDTO bizMsgDTO) throws IOException{
        log.info("Sending business message...");
        //这里必须创建CorrelationData，在发送中带过去，然后在com.huangshi.wuji.messaging.rabbitmq.spring.config.SpringRabbitMQConfiguration的Line 162中，才能接收到
        CorrelationData correlationData = new CorrelationData(bizMsgDTO.getBizId()+bizMsgDTO.getMsgId());

        /*
        Order order = new Order();
        order.setId(1);
        order.setUserId(1000);
        order.setAmout(88d);
        order.setTime(LocalDateTime.now().toString());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(order);
        System.out.println(json);

        rabbitTemplate.convertAndSend("","zhihao.miao.order",json);
        */

//        sendMessageViaClient();
        rabbitTemplate.convertAndSend(SpringRabbitMQConstants.Biz_Topic_Exchange_Name, SpringRabbitMQConstants.BIZ_Routing_Key, bizMsgDTO, correlationData);
    }

    @Override
    public void sendOperationMessage(OperationEntityDTO opeDTO) {
        log.info("Sending operation message...");
    }




}
