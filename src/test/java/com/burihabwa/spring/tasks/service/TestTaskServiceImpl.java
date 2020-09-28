package com.burihabwa.spring.tasks.service;

import com.burihabwa.spring.tasks.dao.FakeTaskDAO;
import com.burihabwa.spring.tasks.models.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

public class TestTaskServiceImpl {
    @Test
    public void testNoTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Assert.assertEquals(service.list().size(), 0);
    }

    @Test
    public void testAddTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Assert.assertEquals(service.list().size(), 0);
        Task task = new Task(null, "title", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        Assert.assertNotNull(newTask.getId());
        Assert.assertEquals(service.list().size(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTaskWithFinishBeforeStart() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Assert.assertEquals(service.list().size(), 0);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = start.minusMinutes(1);
        Task task = new Task(null, "title", "description", start, finish, false);
        service.add(task);
    }

    @Test
    public void testGetExistingTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "existingTask", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        Assert.assertEquals(newTask, service.get(newTask.getId()));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetNonExistingTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        service.get(UUID.randomUUID());
    }

    @Test
    public void testDeleteExistingTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "existingTask", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        Assert.assertEquals(service.list().size(), 1);
        Task deletedTask = service.delete(newTask.getId());
        Assert.assertEquals(deletedTask, newTask);
        Assert.assertEquals(service.list().size(), 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteNonExistingTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        service.delete(UUID.randomUUID());
    }

    @Test
    public void testUpdateExistingTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "existingTask", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        String newTitle = "newTitle";
        task.setTitle(newTitle);
        service.update(newTask.getId(), newTask);
        Task modifiedTask = service.get(newTask.getId());
        Assert.assertEquals(newTitle, modifiedTask.getTitle());
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateNonExistingTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "existingTask", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        service.update(UUID.randomUUID(), task);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateExistingTaskFinishingBeforeStart() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "title", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = start.minusMinutes(1);
        newTask.setStart(start);
        newTask.setFinish(finish);
        service.update(newTask.getId(), newTask);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateExistingTaskWithoutIdUpdatesTask() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "title", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime finish = start.minusMinutes(1);
        newTask.setStart(start);
        newTask.setFinish(finish);
        service.update(newTask.getId(), newTask);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateExistingTaskWithIdMismatch() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "title", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        service.update(UUID.randomUUID(), newTask);
    }

    @Test
    public void testUpdateSetsIdWhenNull() {
        TaskServiceImpl service = new TaskServiceImpl(new FakeTaskDAO());
        Task task = new Task(null, "title", "description", LocalDateTime.now(), LocalDateTime.now(), false);
        Task newTask = service.add(task);
        UUID originalId = newTask.getId();
        newTask.setId(null);
        Assert.assertNull(newTask.getId());
        Task modifiedTask = service.update(originalId, newTask);
        Assert.assertEquals(originalId, modifiedTask.getId());
    }
}
