package com.huangshi.wuji.messaging.app.biz.service;

public interface Bus {


    /**
     * 按照流程：
     * a. 业务数据入库
     * b. a步骤完成，消息记录入库
     * c. 发送消息步骤1 - 重大操作记录入操作表
     * d. 发送消息步骤2 - 发送消息
     */

    boolean doBusiness();

    //业务数据入库
    boolean businessIntoDB();

    //消息数据入库
    boolean messageIntoDB();

    //发送业务消息
    boolean sendBizMessage();

    //发送操作日志消息记录操作日志
    boolean sendOperationMessage();


}
