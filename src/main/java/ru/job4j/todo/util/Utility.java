package ru.job4j.todo.util;

import ru.job4j.todo.model.User;
import javax.servlet.http.HttpSession;

public final class Utility {

    private Utility() {
    }

    public static User check(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Guest");
        }
        return user;
    }
}

