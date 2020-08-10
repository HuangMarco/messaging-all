package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.OperationEntityDTO;

public interface CommonMessageSenderService {

    //发送业务消息
    boolean sendBizMessage(BizMessageEntityDTO bizMsgDTO);

    //发送运维日志记录消息
    boolean sendOperationMessage(OperationEntityDTO opeDTO);

}
