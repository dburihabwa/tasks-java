package com.burihabwa.spring.tasks.dao;

import com.burihabwa.spring.tasks.models.Task;

import java.util.List;
import java.util.UUID;

public interface TaskDAO {
    public Task insert(Task task);

    public Task get(UUID id);

    public List<Task> list();

    Task delete(UUID id);
}
