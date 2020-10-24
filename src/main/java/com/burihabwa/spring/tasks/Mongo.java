package com.burihabwa.spring.tasks;

import com.burihabwa.spring.tasks.dao.TaskDAO;
import com.burihabwa.spring.tasks.dao.TaskRepository;
import com.burihabwa.spring.tasks.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Mongo {
    private static final Logger LOGGER = LoggerFactory.getLogger("tasks");

    public static void main(String[] args) {
        LOGGER.info("Starting application");
        //SpringApplication.run(TasksJavaApplication.class, args);
        TaskDAO dao = new TaskRepository();
        Task task = new Task(UUID.randomUUID(), "Insert into Mongodb", "Insert a task into the tasks collection", LocalDateTime.now(), LocalDateTime.now(), false);
        System.out.println(dao.list());
        dao.insert(task);
        System.out.println(dao.get(task.getId()));
        try {
            dao.get(UUID.randomUUID());
            System.exit(2);
        } catch (NoSuchElementException expected) {
        }
        try {
            dao.update(UUID.randomUUID(), task);
            System.exit(3);
        } catch (NoSuchElementException expected) {
        }

        dao.delete(task.getId());
        System.out.println(dao.list());
    }
}
