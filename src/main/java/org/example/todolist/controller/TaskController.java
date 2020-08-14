package org.example.todolist.controller;

import org.example.todolist.domain.Task;
import org.example.todolist.domain.User;
import org.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/main")
    public String addTask(@AuthenticationPrincipal User user,
                      @RequestParam String title,
                      @RequestParam String description,
                      @RequestParam String tag,
                      Model model
    ) {
        Task task = new Task(title, description, tag, user);
        taskService.save(task);

        Iterable<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "main";
    }

    @GetMapping("/task-list/{user}")
    public String userTasks(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam(required = false) Task task,
            Model model
    ) {

        Set<Task> tasks = user.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task",task);
        model.addAttribute("isCurrentUser", user.equals(currentUser));
        return "userTasks";
    }
    @PostMapping("/task-list/{user}")
    public String updateTask(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam Task task,
            @RequestParam String title,
            @RequestParam String tag,
            @RequestParam String description,
            Model model
    ){
        model.addAttribute("isCurrentUser", user.equals(currentUser));
        taskService.updateTask(task,title,tag,description);
        return "redirect:/task-list/{user}";
    }

}
