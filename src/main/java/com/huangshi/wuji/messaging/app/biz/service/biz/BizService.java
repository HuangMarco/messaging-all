package com.huangshi.wuji.messaging.app.biz.service.biz;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.entity.BizEntity;

import java.text.ParseException;

public interface BizService {

    BizEntityDTO doBusiness(BizEntityDTO bizDTO) throws ParseException;

    BizEntityDTO createBiz(BizEntityDTO bizDTO) throws ParseException;

    //消息数据入库
    boolean bizMsgIntoDB(BizEntityDTO bizDTO);

    //准备待发的业务消息

    BizMessageEntityDTO prepareBizMessage(BizEntityDTO bizDTO) throws ParseException;

}
