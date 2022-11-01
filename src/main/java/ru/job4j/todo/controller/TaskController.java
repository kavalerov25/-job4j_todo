package ru.job4j.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allTasks")
    public String allTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "allTasks";
    }

    @GetMapping("/completedTasks")
    public String completedTasks(Model model) {
        model.addAttribute("tasks", taskService.findCompleted());
        return "completedTasks";
    }

    @GetMapping("/newTasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.findNew());
        return "newTasks";
    }

    @PostMapping("/createTask")
    public String createTask(@ModelAttribute Task task) {
        taskService.create(task);
        return "redirect:/allTasks";
    }

    @GetMapping("/formUpdateTask/{taskId}")
    public String formUpdateTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findById(id));
        return "updateTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task) {
        taskService.update(task);
        return "redirect:/allTasks";
    }

    @GetMapping("/formAddTask")
    public String formAddTask() {
        return "addTask";
    }

    @GetMapping("/complete/{taskId}")
    public String completeTask(@PathVariable("taskId") int id) {
        taskService.setTaskIsDone(taskService.findById(id));
        return "redirect:/allTasks";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") int id) {
        taskService.delete(taskService.findById(id));
        return "redirect:/allTasks";
    }

    @GetMapping("/taskDetails/{taskId}")
    public String taskDetails(@PathVariable("taskId") int id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "taskDetails";
    }
}
