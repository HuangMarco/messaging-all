package com.huangshi.wuji.messaging.app.biz.service;

public interface Bus {


    /**
     * 按照流程：
     * a. 业务数据入库
     * b. a步骤完成，消息记录入库
     * c.
     */

    boolean doBusiness();

    boolean messageIntoDB();

    boolean sendMessage();



}
