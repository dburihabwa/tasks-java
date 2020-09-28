package com.burihabwa.spring.tasks.service;

import com.burihabwa.spring.tasks.models.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task add(Task task);

    Task get(UUID id);

    List<Task> list();

    Task delete(UUID id);

    Task update(UUID id, Task task);
}
