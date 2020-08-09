package com.huangshi.wuji.messaging.app.biz.service;

public interface BusSchedulerForAppA {


    /**
     * 总装流程，也是AppA入口，调用其他分支BizService, CommenMessageService流程执行业务
     * 将业务系统抽象成业务平台
     * 业务平台提供通用业务逻辑支撑
     * 特殊业务另写
     * 按照流程：
     * a. 业务数据入库
     * b. a步骤完成，消息记录入库
     * c. 发送消息步骤1 - 重大操作记录入操作表
     * d. 发送消息步骤2 - 发送消息
     */

    boolean doBusinessForAppA();




}
