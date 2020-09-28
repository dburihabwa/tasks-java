package com.burihabwa.spring.tasks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTaskService {

    @Autowired
    private TaskService service;

    @Test
    public void test() {
        Assertions.assertEquals(service.list().size(), 0);
    }
}
