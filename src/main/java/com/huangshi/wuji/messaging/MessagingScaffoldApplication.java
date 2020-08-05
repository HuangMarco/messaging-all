package com.huangshi.wuji.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
//@EnableJpaRepositories(basePackages="com.huangshi.wuji.messaging")
public class MessagingScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingScaffoldApplication.class, args);
    }

}
