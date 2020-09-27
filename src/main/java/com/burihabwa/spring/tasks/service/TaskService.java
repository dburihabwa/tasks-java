package com.burihabwa.spring.tasks.service;

import com.burihabwa.spring.tasks.dao.TaskDAO;
import com.burihabwa.spring.tasks.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private TaskDAO taskDao;

    @Autowired
    public TaskService(@Qualifier("fakeDAO") TaskDAO taskDao) {
        this.taskDao = taskDao;
    }

    public Task add(Task task) {
        return this.taskDao.insert(task);
    }

    public Task get(UUID id) {
        return this.taskDao.get(id);
    }

    public List<Task> list() {
        return this.taskDao.list();
    }
}
