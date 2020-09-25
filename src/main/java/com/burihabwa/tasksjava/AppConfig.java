package com.burihabwa.tasksjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Configuration
public class AppConfig {

    public @Bean
    MongoClientFactoryBean mongoClient() {
        MongoClientFactoryBean bean = new MongoClientFactoryBean();
        bean.setHost("localhost");
        return bean;
    }
}
