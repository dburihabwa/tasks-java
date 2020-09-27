package com.burihabwa.tasksjava.controllers;

import com.burihabwa.tasksjava.models.Task;
import com.burihabwa.tasksjava.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping()
    public Task add(@RequestBody Task task) {
        return this.service.add(task);
    }
}
