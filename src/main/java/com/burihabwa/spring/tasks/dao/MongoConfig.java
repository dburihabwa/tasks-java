package com.burihabwa.spring.tasks.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "mongo.properties")
public class MongoConfig {
    @Value("mongo_host")
    public String host;

    @Value("mongo_port")
    public String port;
}
