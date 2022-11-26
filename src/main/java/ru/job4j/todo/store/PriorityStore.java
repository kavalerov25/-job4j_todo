package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriorityStore {
    private final CrudRepository crudRepository;
    public Optional<Priority> findById(int id) {
        return crudRepository.optional(
                "from Priority where id = :pId", Priority.class,
                Map.of("pId", id));
    }


    public List<Priority> findAll() {
        return crudRepository.query("FROM Priority order by id", Priority.class);
    }
}
