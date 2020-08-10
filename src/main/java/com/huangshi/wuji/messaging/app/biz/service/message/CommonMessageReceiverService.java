package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.msgcontent.BizRelatedMessage;

public interface CommonMessageReceiverService {

    public void receiveMessage(final BizMessageEntityDTO bizMsg);
}
