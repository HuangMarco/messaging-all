package com.huangshi.wuji.messaging.app.messaging.dlx.controller;

import com.huangshi.wuji.messaging.app.messaging.dlx.constants.DelayTypeEnum;
import com.huangshi.wuji.messaging.app.messaging.dlx.sender.DelayMessageSender;
import com.huangshi.wuji.messaging.swagger.config.SwaggerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

@Slf4j
@RequestMapping(SwaggerConstants.API_RabbitMQ_DLX)
@RestController
public class RabbitMQConfigForTTLDLXController {

    @Autowired
    private DelayMessageSender sender;

    @RequestMapping("sendmsg")
    public void sendMsg(String msg, Integer delayType){
        log.info("当前时间：{},收到请求，msg:{},delayType:{}", new Date(), msg, delayType);
        sender.sendMsg(msg, Objects.requireNonNull(DelayTypeEnum.getDelayTypeEnumByValue(delayType)));
    }
}
