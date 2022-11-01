package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.Collection;

@Service
public class TaskService {

    private final TaskStore store;

    public TaskService(TaskStore store) {
        this.store = store;
    }

    public Task create(Task task) {
        return store.create(task);
    }

    public void update(Task task) {
        store.update(task);
    }

    public void delete(Task task) {
        store.delete(task);
    }

    public void setTaskIsDone(Task task) {
        store.setTaskIsDone(task);
    }

    public Collection<Task> findAll() {
        return store.findAll();
    }

    public Collection<Task> findNew() {
        return store.findNew();
    }

    public Collection<Task> findCompleted() {
        return store.findCompleted();
    }

    public Task findById(int id) {
        return store.findById(id);
    }
}
