package com.huangshi.wuji.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
@EnableScheduling
//@EnableJpaRepositories(basePackages="com.huangshi.wuji.messaging")
public class MessagingScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingScaffoldApplication.class, args);
    }

}
