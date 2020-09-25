package com.burihabwa.tasksjava.models;

import java.time.LocalDateTime;

public class Task {
    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime finish;
    private boolean completed;

    public Task(String title, String description, LocalDateTime start, LocalDateTime finish, boolean completed) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", completed=" + completed +
                '}';
    }
}
