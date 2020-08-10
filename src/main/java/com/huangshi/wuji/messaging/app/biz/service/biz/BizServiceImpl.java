package com.huangshi.wuji.messaging.app.biz.service.biz;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
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

    @Override
    public BizEntityDTO createBiz(BizEntityDTO bizDTO) throws ParseException{
        BizEntity bizEntity = convertToEntity(bizDTO);
        BizEntityDTO savedBizDTO = convertToDto(bizRepo.save(bizEntity));
        return savedBizDTO;
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
