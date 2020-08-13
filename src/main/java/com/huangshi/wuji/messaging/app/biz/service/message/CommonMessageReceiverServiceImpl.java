package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.msgcontent.BizRelatedMessage;
import com.huangshi.wuji.messaging.rabbitmq.spring.constant.SpringRabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CommonMessageReceiverServiceImpl implements CommonMessageReceiverService {

    private static final Logger log = LoggerFactory.getLogger(CommonMessageReceiverServiceImpl.class);

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "queue001", durable = "true"),
//            exchange = @Exchange(value = "exchange001", durable = "true",
//                    type = "topic", ignoreDeclarationExceptions = "true"),
//            key = "springboot.*"
//    ))
    @Override
    @RabbitListener(queues = SpringRabbitMQConstants.BIZ_Queue)
    public void receiveMessage(final BizMessageEntityDTO bizMsgDTO) {
        log.info("start receiving message...");
        log.info("Received biz message: {}", bizMsgDTO.toString());
    }



}
