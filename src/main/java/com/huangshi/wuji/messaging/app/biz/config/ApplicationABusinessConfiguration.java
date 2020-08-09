package com.huangshi.wuji.messaging.app.biz.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationABusinessConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
