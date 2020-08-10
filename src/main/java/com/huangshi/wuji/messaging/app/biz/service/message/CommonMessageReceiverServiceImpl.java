package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.msgcontent.BizRelatedMessage;
import com.huangshi.wuji.messaging.rabbitmq.spring.constant.SpringRabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class CommonMessageReceiverServiceImpl implements CommonMessageReceiverService {

    private static final Logger log = LoggerFactory.getLogger(CommonMessageReceiverServiceImpl.class);
    @Override
    @RabbitListener(queues = SpringRabbitMQConstants.Message_BIZ_Queue)
    public void receiveMessage(final BizMessageEntityDTO bizMsg) {
        log.info("Received message as specific class: {}", bizMsg.toString());
    }



}
