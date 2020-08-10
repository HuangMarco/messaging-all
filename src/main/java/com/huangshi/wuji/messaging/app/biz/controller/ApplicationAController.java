package com.huangshi.wuji.messaging.app.biz.controller;

import com.huangshi.wuji.messaging.app.biz.dto.BizEntityDTO;
import com.huangshi.wuji.messaging.app.biz.entity.BizEntity;
import com.huangshi.wuji.messaging.app.biz.service.BusSchedulerForAppA;
import com.huangshi.wuji.messaging.swagger.config.SwaggerConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(SwaggerConstants.API_Application_A)
public class ApplicationAController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BusSchedulerForAppA busForAppA;

    @RequestMapping(value = "/create/order",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BizEntityDTO createBusinessForAppicationA(@RequestBody BizEntityDTO bizDTO) throws ParseException{
        BizEntity bizEntity = convertToEntity(bizDTO);
        busForAppA.doBusinessForAppA(bizDTO);
        // TODO: 2020/8/9
        return new BizEntityDTO();
    }

    @PutMapping(value = "/{businessId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody BizEntityDTO bizDTO) throws ParseException{
        BizEntity bizEntity = convertToEntity(bizDTO);

    }

    private BizEntity convertToEntity(BizEntityDTO bizDTO) throws ParseException {
        BizEntity bizEntity = modelMapper.map(bizDTO, BizEntity.class);

        return bizEntity;
    }

    private BizEntityDTO convertToDto(BizEntity bizEntity) throws ParseException{
        BizEntityDTO bizDTO = modelMapper.map(bizEntity, BizEntityDTO.class);
//        bizDTO.setCreatedDate(bizEntity.getCreatedDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
        return bizDTO;
    }
}
