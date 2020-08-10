package com.huangshi.wuji.messaging.app.biz.service.impl;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.service.BusSchedulerForAppA;
import com.huangshi.wuji.messaging.app.biz.service.biz.BizService;
import com.huangshi.wuji.messaging.app.biz.service.message.CommonMessageService;
import com.huangshi.wuji.messaging.app.biz.service.operation.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class BusSchedulerForAppAImpl implements BusSchedulerForAppA {

    public static final Logger logger = LoggerFactory.getLogger(BusSchedulerForAppAImpl.class);

    @Autowired
    private BizService bizService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private CommonMessageService commonMsgService;

    /**
     * 进行业务组装,
     * // TODO: 2020/8/9  后期进行设计模式优化
     * @return
     */
    @Override
    public BizEntityDTO doBusinessForAppA(BizEntityDTO bizDTO) throws ParseException {

        //主业务
        BizEntityDTO updatedBizEntityDTO = bizService.createBiz(bizDTO);
        //埋点-记录操作日志
        operationService.sendOperationMessage();
        //消息入库
        commonMsgService.messageIntoDB();
        //发送消息
        commonMsgService.sendMessage();

        return updatedBizEntityDTO;
    }
}
