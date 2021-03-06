package com.burihabwa.spring.tasks.dao;

import com.burihabwa.spring.tasks.models.Task;
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
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        throw new NoSuchElementException("Cannot find task " + id);
    }

    @Override
    public List<Task> list() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task delete(UUID id) {
        Task task = tasks.remove(id);
        if (task == null) {
            throw new NoSuchElementException("Cannot find task " + id);
        }
        return task;
    }

    @Override
    public Task update(UUID id, Task task) {
        if (!tasks.containsKey(id)) {
            throw new NoSuchElementException("Cannot find task " + id);
        }
        return tasks.put(id, task);
    }
}
