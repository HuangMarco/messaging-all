package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.OperationEntityDTO;

import java.io.IOException;

public interface CommonMessageSenderService {

    //发送业务消息
    void sendBizMessage(BizMessageEntityDTO bizMsgDTO) throws IOException;

    //发送运维日志记录消息
    void sendOperationMessage(OperationEntityDTO opeDTO) throws IOException;

}
