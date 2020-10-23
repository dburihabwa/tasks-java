package com.burihabwa.spring.tasks.controllers;

import com.burihabwa.spring.tasks.models.Task;
import com.burihabwa.spring.tasks.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger("tasks");

    @Autowired
    public TaskController(TaskService service) {
        logger.info("Starting TaskController");
        this.service = service;
    }

    @PostMapping
    public Task add(@RequestBody Task task) {
        Task inserted = this.service.add(task);
        logger.info("inserted new task as " + task.getId());
        return inserted;
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable String id) {
        try {
            return this.service.get(UUID.fromString(id));
        } catch (NoSuchElementException e) {
            logger.warn("could not find " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ENTITY_NOT_FOUND_MESSAGE);
        }
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable String id) {
        Task deleted = null;
        try {
            deleted = this.service.delete(UUID.fromString(id));
        } catch (NoSuchElementException e) {
            logger.warn("could not delete " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ENTITY_NOT_FOUND_MESSAGE);
        }
        logger.info("deleted task " + deleted.getId());
        return deleted;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable String id, @RequestBody Task task) {
        Task updated = null;
        try {
            updated = this.service.update(UUID.fromString(id), task);
        } catch (NoSuchElementException e) {
            logger.warn("could not update " + task.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ENTITY_NOT_FOUND_MESSAGE);
        }
        logger.info("updated task " + task.getId());
        return updated;
    }


    @GetMapping
    public List<Task> list() {
        return this.service.list();
    }
}
