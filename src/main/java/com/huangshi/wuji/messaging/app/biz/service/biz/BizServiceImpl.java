package com.huangshi.wuji.messaging.app.biz.service.biz;

import org.springframework.stereotype.Service;

@Service
public class BizServiceImpl implements BizService {

    @Override
    public boolean doBusiness() {
        return false;
    }

    @Override
    public boolean businessIntoDB() {
        return false;
    }

    @Override
    public boolean sendBizMessage() {
        return false;
    }

    @Override
    public boolean messageIntoDB() {
        return false;
    }
}
