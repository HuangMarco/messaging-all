package com.huangshi.wuji.messaging.app.biz.service.operation;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;

public interface OperationService {

    //发送操作日志消息记录操作日志
    boolean sendOperationMessage(BizEntityDTO bizDTO);
}
