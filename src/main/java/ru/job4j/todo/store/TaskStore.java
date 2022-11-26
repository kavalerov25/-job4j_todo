package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final CrudRepository crudRepository;

    public List<Task> findAll() {
        return crudRepository.query(
                "from Task t join fetch t.user join fetch t.priority order by t.id",
                Task.class
        );
    }

    public List<Task> findByDone(boolean isDone) {
        return crudRepository.query(
                "from Task t join fetch t.user join fetch t.priority where done = :isDone",
                Task.class,
                Map.of("isDone", isDone)
        );
    }

    public Optional<Task> findById(int id) {
        return crudRepository.optional(
                "from Task t join fetch t.user join fetch t.priority where id = :tId",
                Task.class,
                Map.of("tId", id)
        );
    }

    public Task add(Task task) {
        crudRepository.run(session -> session.persist(task));
        return task;
    }

    public void delete(int id) {
        crudRepository.run("delete from Task where id = :tId",
                Map.of("tId", id));
    }

    public void replace(int id, Task task) {
        crudRepository.run("update Task set description = :tDesc, created = :tCrt, done = false,"
                      + " user_id = :tUser, priority_id = :tPriority where id = :tId",
                Map.of("tId", id, "tDesc", task.getDescription(), "tCrt", LocalDateTime.now(),
                        "tUser", task.getUser().getId(), "tPriority", task.getPriority()));
    }

    public void setDone(int id) {
        crudRepository.run("update Task set done = true where id = :tId",
                Map.of("tId", id));
    }
}

