package com.huangshi.wuji.messaging.app.biz.service.message;

public interface CommonMessageService {

    //消息数据入库
    boolean messageIntoDB();

    //发送消息
    boolean sendMessage();

}
