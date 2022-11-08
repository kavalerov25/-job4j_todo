package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "allTasks";
    }

    @GetMapping("/undone")
    public String undone(Model model) {
        model.addAttribute("undone", taskService.findByDone(false));
        return "undone";
    }

    @GetMapping("/done")
    public String done(Model model) {
        model.addAttribute("done", taskService.findByDone(true));
        return "done";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task) {
        task.setCreated(LocalDateTime.now());
        taskService.create(task);
        return "redirect:/tasks";
    }

    @GetMapping("/info/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isEmpty()) {
            return "404";
        }
        model.addAttribute("task", optionalTask.get());
        return "info";
    }

    @GetMapping("/setDone/{id}")
    public String setDone(@PathVariable("id") int id) {
        taskService.setTaskIsDone(id);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task) {
        taskService.update(task.getId(), task);
        return "redirect:/tasks";
    }

    @GetMapping("/formUpdateTask/{id}")
    public String formUpdateTask(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "updateTask";
    }
}
