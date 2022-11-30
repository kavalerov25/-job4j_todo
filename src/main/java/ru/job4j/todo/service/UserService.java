package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.UserStore;

import java.util.Optional;
import java.util.TimeZone;

@Service
@AllArgsConstructor
public class UserService {
    private final UserStore store;

    public Optional<User> add(User user) {
        if (user.getTimezone() == null) {
            user.setTimezone(TimeZone.getDefault());
        }
        return store.add(user);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return store.findByLoginAndPassword(login, password);
    }

    public Optional<User> findById(int id) {
        return store.findById(id);
    }

}

