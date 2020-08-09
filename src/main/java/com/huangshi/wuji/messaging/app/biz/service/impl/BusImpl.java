package com.huangshi.wuji.messaging.app.biz.service.impl;

import com.huangshi.wuji.messaging.app.biz.service.Bus;

public class BusImpl implements Bus {

    @Override
    public boolean doBusiness() {
        
        return false;
    }

    @Override
    public boolean businessIntoDB() {
        return false;
    }

    @Override
    public boolean messageIntoDB() {
        return false;
    }

    @Override
    public boolean sendBizMessage() {
        return false;
    }

    @Override
    public boolean sendOperationMessage() {
        return false;
    }
}
