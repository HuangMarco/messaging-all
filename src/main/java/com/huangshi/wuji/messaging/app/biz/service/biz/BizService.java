package com.huangshi.wuji.messaging.app.biz.service.biz;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.entity.BizEntity;

import java.text.ParseException;

public interface BizService {

    boolean doBusiness();

    BizEntityDTO createBiz(BizEntityDTO bizDTO) throws ParseException;

    //业务数据入库
    boolean businessIntoDB();

    //发送业务消息
    boolean sendBizMessage();

    //消息数据入库
    boolean messageIntoDB();
}
