package com.burihabwa.tasksjava.dao;

import com.burihabwa.tasksjava.models.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDAO")
public class FakeTaskDAO implements TaskDAO {
    private Map<UUID, Task> tasks = new HashMap<>();

    @Override
    public Task insert(Task task) {
        UUID id = UUID.randomUUID();
        tasks.put(id, task);
        task.setId(id);
        return task;
    }

    @Override
    public Task get(UUID id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> list() {
        return new ArrayList<>(tasks.values());
    }
}
