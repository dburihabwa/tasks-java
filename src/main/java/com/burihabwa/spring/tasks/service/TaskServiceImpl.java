package com.burihabwa.spring.tasks.service;

import com.burihabwa.spring.tasks.dao.TaskDAO;
import com.burihabwa.spring.tasks.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskDAO taskDao;

    @Autowired
    public TaskServiceImpl(@Qualifier("fakeDAO") TaskDAO taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task add(Task task) {
        return this.taskDao.insert(task);
    }

    @Override
    public Task get(UUID id) {
        return this.taskDao.get(id);
    }

    @Override
    public List<Task> list() {
        return this.taskDao.list();
    }

    @Override
    public Task delete(UUID id) {
        return this.taskDao.delete(id);
    }

    @Override
    public Task update(UUID id, Task task) {
        return this.taskDao.update(id, task);
    }
}
