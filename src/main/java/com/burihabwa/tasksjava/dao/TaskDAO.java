package com.burihabwa.tasksjava.dao;

import com.burihabwa.tasksjava.models.Task;

import java.util.UUID;

public interface TaskDAO {
    public Task insert(Task task);

    public Task get(UUID id);
}
