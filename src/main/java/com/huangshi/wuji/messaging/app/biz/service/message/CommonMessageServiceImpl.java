package com.huangshi.wuji.messaging.app.biz.service.message;

import com.huangshi.wuji.messaging.app.messaging.repository.MessagingEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonMessageServiceImpl implements CommonMessageService{

    @Autowired
    private MessagingEntityRepository messageRepo;

    @Override
    public boolean messageIntoDB() {
        return false;
    }

    @Override
    public boolean sendMessage() {
        return false;
    }
}
