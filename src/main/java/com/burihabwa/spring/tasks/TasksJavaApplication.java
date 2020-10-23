package com.burihabwa.spring.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksJavaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger("tasks");

    public static void main(String[] args) {
        LOGGER.info("Starting application");
        SpringApplication.run(TasksJavaApplication.class, args);
    }

}
