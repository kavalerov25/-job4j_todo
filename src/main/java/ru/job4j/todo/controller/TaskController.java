package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;
import ru.job4j.todo.util.Utility;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final static String MESSAGE = "Приложение \"TODO список\". Примененный стек технологий: "
                                          + "Spring boot, Thymeleaf, Bootstrap, Hibernate, PostgreSql.";
    private final TaskService taskService;
    private final PriorityService priorityService;

    @GetMapping("/new")
    public String add(Model model, HttpSession session) {
        model.addAttribute("user", Utility.check(session));
        model.addAttribute("priorities", priorityService.findAll());
        return "new";
    }

    @GetMapping("")
    public String tasks(Model model, HttpSession session) {
        model.addAttribute("message", MESSAGE);
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("user", Utility.check(session));
        return "tasks";
    }

    @GetMapping("/undone")
    public String undone(Model model, HttpSession session) {
        model.addAttribute("undone", taskService.findByDone(false));
        model.addAttribute("user", Utility.check(session));
        return "undone";
    }

    @GetMapping("/done")
    public String done(Model model, HttpSession session) {
        model.addAttribute("done", taskService.findByDone(true));
        model.addAttribute("user", Utility.check(session));
        return "done";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, HttpSession session) {
        User user = (User) session.getAttribute("user");
        task.setUser(user);
        task.setCreated(LocalDateTime.now());
        taskService.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/info/{id}")
    public String info(Model model, @PathVariable("id") int id, HttpSession session) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return "404";

        }
        model.addAttribute("task", optionalTask.get());
        model.addAttribute("user", Utility.check(session));
        return "info";
    }


    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id, HttpSession session) {
        Optional<Task> optTask = taskService.findById(id);
        if (optTask.isEmpty()) {
            return "404";
        }
        model.addAttribute("task", optTask.get());

        model.addAttribute("user", Utility.check(session));
        return "formUpdate";
    }

    @GetMapping("/setDone/{id}")
    public String setDone(@PathVariable("id") int id) {
        taskService.setDone(id);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task) {
        taskService.replace(task.getId(), task);
        return "redirect:/tasks";
    }

    @GetMapping("/formUpdateTask/{id}")
    public String formUpdateTask(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "updateTask";
    }

}
