package com.burihabwa.tasksjava.controllers;

import com.burihabwa.tasksjava.models.Task;
import com.burihabwa.tasksjava.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task add(@RequestBody Task task) {
        return this.service.add(task);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable String id) {
        return this.service.get(UUID.fromString(id));
    }

    @GetMapping
    public List<Task> list() {
        return this.service.list();
    }
}
