package com.huangshi.wuji.messaging.app.biz.service.impl;

import com.huangshi.wuji.messaging.app.biz.service.BusSchedulerForAppA;
import com.huangshi.wuji.messaging.app.biz.service.biz.BizService;
import com.huangshi.wuji.messaging.app.biz.service.message.CommonMessageService;
import com.huangshi.wuji.messaging.app.biz.service.operation.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusSchedulerForAppAImpl implements BusSchedulerForAppA {

    public static final Logger logger = LoggerFactory.getLogger(BusSchedulerForAppAImpl.class);

    @Autowired
    private BizService bizService;

    @Autowired
    private CommonMessageService commonMsgService;

    @Autowired
    private OperationService operationService;

    /**
     * 进行业务组装,
     * // TODO: 2020/8/9  后期进行设计模式优化
     * @return
     */
    @Override
    public boolean doBusinessForAppA() {
        return false;
    }
}
