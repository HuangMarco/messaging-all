package com.huangshi.wuji.messaging.app.biz.service.biz;

import com.huangshi.wuji.messaging.annotation.MessageLog;
import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.BizMessageEntityDTO;
import com.huangshi.wuji.messaging.app.biz.entity.BizEntity;
import com.huangshi.wuji.messaging.app.biz.entity.BizMessageEntity;
import com.huangshi.wuji.messaging.app.biz.repository.BizEntityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
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
    public BizMessageEntityDTO prepareBizMessage(BizEntityDTO bizDTO) throws ParseException{
        //因为BizMessageEntityDTO部分属性名与BizEntityDTO不同，所以手动增加匹配规则
        /*mapper.typeMap(BizEntityDTO.class, BizMessageEntityDTO.class).addMappings( mp -> {
            mp.map(src -> src.getBusinessId(), Destination::);
        });*/

        //注意这里不能使用成员变量的mapper，因为每次占用的时候都会使用同一个mapper，会导致重复绑定出错
        ModelMapper mapperNew = new ModelMapper();

        PropertyMap<BizEntityDTO, BizMessageEntityDTO> personMap = new PropertyMap<BizEntityDTO, BizMessageEntityDTO>() {
            protected void configure() {
                map().setBizId(source.getBusinessId());
            }
        };

        mapperNew.addMappings(personMap);

        BizMessageEntityDTO messageDTO = mapperNew.map(bizDTO, BizMessageEntityDTO.class);
        return messageDTO;
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
