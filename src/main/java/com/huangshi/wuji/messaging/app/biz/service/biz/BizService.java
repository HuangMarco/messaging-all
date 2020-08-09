package com.huangshi.wuji.messaging.app.biz.service.biz;

public interface BizService {

    boolean doBusiness();

    //业务数据入库
    boolean businessIntoDB();

    //发送业务消息
    boolean sendBizMessage();

    //消息数据入库
    boolean messageIntoDB();
}
