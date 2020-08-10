package com.huangshi.wuji.messaging.app.biz.service.operation;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.dto.OperationEntityDTO;
import com.huangshi.wuji.messaging.app.biz.service.message.CommonMessageSenderService;
import com.huangshi.wuji.messaging.app.biz.util.BizConstants;
import com.huangshi.wuji.messaging.app.messaging.repository.OperationEntityRepository;
import com.huangshi.wuji.messaging.app.util.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationEntityRepository operationRepo;

    @Autowired
    private CommonMessageSenderService msgSenderService;

    /**
     * 根据BizEntityDTO,建造OperationEntityDTO,维护操作记录日志
     * @param bizDTO
     * @return
     */
    @Override
    public boolean sendOperationMessage(BizEntityDTO bizDTO) {

        OperationEntityDTO opeDTO = produceOperationEntityDTO(bizDTO);

        return msgSenderService.sendOperationMessage(opeDTO);
    }

    private OperationEntityDTO produceOperationEntityDTO(BizEntityDTO bizDTO){

        OperationEntityDTO dto = new OperationEntityDTO();

        //为operationId运用命名规则
        String operationId = String.valueOf(new SequenceGenerator(10).nextId());

        dto.setOperationId(operationId);
        dto.setOperationType(BizConstants.Operation_Type_Create_Biz);
        dto.setApplicationId(BizConstants.Application_Web);
        dto.setNamespaceId(BizConstants.Namespace_Application_Web);
        dto.setBizType(BizConstants.Biz_Type);

        dto.setCreatedDate(bizDTO.getCreatedDate());
        dto.setOperationContent("Create Biz Object Recorded");

        return dto;

    }
}
