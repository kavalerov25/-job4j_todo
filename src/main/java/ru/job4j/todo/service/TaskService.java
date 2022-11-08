package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskStore store;

    public TaskService(TaskStore store) {
        this.store = store;
    }

    public Task create(Task task) {
        return store.create(task);
    }

    public void update(int  id, Task task) {
        store.update(id, task);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public void setTaskIsDone(int id) {
        store.setTaskIsDone(id);
    }

    public Collection<Task> findAll() {
        return store.findAll();
    }

    public List<Task> findByDone(boolean isDone) {
        return store.findByDone(isDone);
    }

    public Optional<Task> findById(int id) {
        return store.findById(id);
    }

}
