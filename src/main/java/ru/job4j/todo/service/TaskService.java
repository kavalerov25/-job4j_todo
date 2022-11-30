package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.TaskStore;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class TaskService {

    private final TaskStore store;

    public TaskService(TaskStore store) {
        this.store = store;
    }

    public List<Task> findAll(User user) {
        var stored = store.findAll();
        var tz = user.getTimezone() != null ? user.getTimezone() : TimeZone.getDefault();
        for (Task task : stored) {
            var time = task.getCreated().atZone(
                    ZoneId.of(tz.getID())
            ).toLocalDateTime();
            task.setCreated(time);
        }
        return stored;
    }

    public List<Task> findByDone(boolean isDone) {
        return store.findByDone(isDone);
    }

    public Optional<Task> findById(int id) {
        return store.findById(id);
    }

    public Task add(Task task) {
        return store.add(task);
    }

    public void delete(int id) {
        store.delete(id);

    }

    public void replace(int id, Task task) {
        store.replace(id, task);
    }

    public void setDone(int id) {
        store.setDone(id);
    }
}
