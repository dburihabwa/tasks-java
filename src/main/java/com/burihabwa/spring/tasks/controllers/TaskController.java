package com.burihabwa.spring.tasks.controllers;

import com.burihabwa.spring.tasks.models.Task;
import com.burihabwa.spring.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    public static final String ENTITY_NOT_FOUND_MESSAGE = "entity not found";
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
        try {
            return this.service.get(UUID.fromString(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ENTITY_NOT_FOUND_MESSAGE);
        }
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable String id) {
        try {
            return this.service.delete(UUID.fromString(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ENTITY_NOT_FOUND_MESSAGE);
        }
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable String id, @RequestBody Task task) {
        try {
            return this.service.update(UUID.fromString(id), task);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ENTITY_NOT_FOUND_MESSAGE);
        }
    }


    @GetMapping
    public List<Task> list() {
        return this.service.list();
    }
}
