package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.OperationEntityDTO;
import com.huangshi.wuji.messaging.app.biz.msgcontent.BizRelatedMessage;
import com.huangshi.wuji.messaging.app.messaging.repository.MessagingEntityRepository;
import com.huangshi.wuji.messaging.rabbitmq.spring.constant.SpringRabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonMessageSenderServiceImpl implements CommonMessageSenderService {

    /**
     * Message的配置见：com.huangshi.wuji.messaging.rabbitmq.spring.config.SpringRabbitMQConfiguration
     */


    @Autowired
    private MessagingEntityRepository messageRepo;

    private static final Logger log = LoggerFactory.getLogger(CommonMessageSenderServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    public CommonMessageSenderServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @Scheduled(fixedDelay = 3000L)
    @Override
    @RabbitListener(queues = SpringRabbitMQConstants.BIZ_Queue)
    public void sendBizMessage(BizMessageEntityDTO bizMsgDTO) {
        log.info("Sending business message...");
        rabbitTemplate.convertAndSend(SpringRabbitMQConstants.Biz_Topic_Exchange_Name, SpringRabbitMQConstants.BIZ_Routing_Key, bizMsgDTO);
    }

    @Override
    public void sendOperationMessage(OperationEntityDTO opeDTO) {
        log.info("Sending operation message...");
    }




}
