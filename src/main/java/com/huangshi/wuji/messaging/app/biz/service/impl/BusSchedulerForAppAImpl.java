package com.huangshi.wuji.messaging.app.biz.service.impl;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.service.BusSchedulerForAppA;
import com.huangshi.wuji.messaging.app.biz.service.biz.BizService;
import com.huangshi.wuji.messaging.app.biz.service.message.CommonMessageSenderService;
import com.huangshi.wuji.messaging.app.biz.service.operation.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
public class BusSchedulerForAppAImpl implements BusSchedulerForAppA {

    public static final Logger logger = LoggerFactory.getLogger(BusSchedulerForAppAImpl.class);

    @Autowired
    private BizService bizService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private CommonMessageSenderService commonMsgSenderService;

    /**
     * 总线中进行业务组装:
     * a.设计业务方法createBiz: AOP自动触发消息发送动作
     * b.为降低系统耦合度，运维日志也通过消息发送至运维日志库中（与message同库）
     * // TODO: 2020/8/9  后期进行设计模式优化
     * @return
     */
    @Override
    public BizEntityDTO doBusinessForAppA(BizEntityDTO bizDTO) throws ParseException, IOException {

        //主业务
        BizEntityDTO updatedBizEntityDTO = bizService.doBusiness(bizDTO);

        //准备业务相关消息对象
        BizMessageEntityDTO bizMessageDTO = bizService.prepareBizMessage(updatedBizEntityDTO);

        //发送业务相关消息
        commonMsgSenderService.sendBizMessage(bizMessageDTO);

        // TODO: 2020/8/10 运维日志也是调用CommonMessageSenderService,后续考虑通知模式,
        // 类似：我准备好了，通知你你自己去待发送的资源池中去取然后发送，也类似于任务调度，但是需要衡量哪种方式更好，还是保留现状？
        //运维埋点-记录操作日志
        operationService.sendOperationMessage(updatedBizEntityDTO);


        return updatedBizEntityDTO;
    }
}
