package com.huangshi.wuji.messaging.app.biz.service.biz;

import com.huangshi.wuji.messaging.annotation.MessageLog;
import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.entity.BizEntity;
import com.huangshi.wuji.messaging.app.biz.repository.BizEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class BizServiceImpl implements BizService {

    @Autowired
    private BizEntityRepository bizRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BizEntityDTO doBusiness(BizEntityDTO bizDTO) throws ParseException{

        BizEntityDTO savedBizDTO = createBiz(bizDTO);

        bizMsgIntoDB(savedBizDTO);

        return savedBizDTO;
    }

    @Override
    public boolean bizMsgIntoDB(BizEntityDTO bizDTO) {

        return true;
    }

    //打上该注解表示该方法要通过消息记录日志并入库
    @MessageLog
    @Override
    public BizEntityDTO createBiz(BizEntityDTO bizDTO) throws ParseException{
        BizEntity bizEntity = convertToEntity(bizDTO);
        BizEntityDTO savedBizDTO = convertToDto(bizRepo.save(bizEntity));
        return savedBizDTO;
    }

    @Override
    public BizMessageEntityDTO prepareBizMessage(BizEntityDTO bizDTO) {
        return null;
    }

    private BizEntity convertToEntity(BizEntityDTO bizDTO) throws ParseException {
        BizEntity bizEntity = mapper.map(bizDTO, BizEntity.class);
        return bizEntity;
    }

    private BizEntityDTO convertToDto(BizEntity bizEntity) throws ParseException{
        BizEntityDTO bizDTO = mapper.map(bizEntity, BizEntityDTO.class);
//        bizDTO.setCreatedDate(bizEntity.getCreatedDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
        return bizDTO;
    }

}
