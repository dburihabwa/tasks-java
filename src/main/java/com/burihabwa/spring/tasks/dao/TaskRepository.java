package com.burihabwa.spring.tasks.dao;

import com.burihabwa.spring.tasks.models.Task;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Repository("mongoDAO")
public class TaskRepository implements TaskDAO {
    public static final String COLLECTION_NAME = "tasks";
    private Logger logger = LoggerFactory.getLogger("dao");
    private final MongoCollection<Document> collection;

    private static Task toTask(Document document) {
        String oid = (String) document.get("_id");
        UUID id = UUID.fromString(oid);
        String title = document.getString("title");
        String description = document.getString("description");
        LocalDateTime start = ((Date) document.get("start")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime finish = ((Date) document.get("finish")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        boolean isCompleted = document.getBoolean("completed");
        return new Task(id, title, description, start, finish, isCompleted);
    }

    private static Document toDocument(Task task) {
        Document document = new Document();
        document.put("_id", task.getId().toString());
        document.put("title", task.getTitle());
        document.put("description", task.getDescription());
        document.put("start", task.getStart());
        document.put("finish", task.getFinish());
        document.put("completed", task.isCompleted());
        return document;
    }

    public TaskRepository() {
        MongoClient client = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = client.getDatabase(COLLECTION_NAME);
        if (database.getCollection(COLLECTION_NAME) == null) {
            database.createCollection(COLLECTION_NAME);
        }
        collection = database.getCollection("tasks");
    }

    @Override
    public Task insert(Task task) {
        task.setId(UUID.randomUUID());
        logger.debug("Inserting task " + task.getId());
        collection.insertOne(toDocument(task));
        return task;
    }

    @Override
    public Task get(UUID id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id.toString());
        logger.debug("Looking for " + id);
        FindIterable<Document> results = collection.find(query);
        Document doc = results.first();
        if (doc == null) {
            logger.warn("Could not find task " + id);
            throw new NoSuchElementException("Could not find task " + id);
        }
        return toTask(doc);
    }

    @Override
    public List<Task> list() {
        List<Task> tasks = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            tasks.add(toTask(document));
        }
        return tasks;
    }

    @Override
    public Task delete(UUID id) {
        Task task = get(id);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id.toString());
        collection.deleteOne(query);
        return task;
    }

    @Override
    public Task update(UUID id, Task task) {
        get(id);
        logger.debug("Found task " + id);
        task.setId(id);
        logger.debug("Modifying task " + id);
        Task modified = insert(task);
        logger.debug("Modified task " + id);
        return modified;
    }
}
