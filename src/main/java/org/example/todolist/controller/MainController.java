package org.example.todolist.controller;

import org.example.todolist.domain.Task;
import org.example.todolist.domain.User;
import org.example.todolist.repos.TaskRepo;
import org.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String greeting(Model model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Task> tasks;
        if (filter != null && !filter.isEmpty()) {
            tasks = taskService.findByTag(filter);
        } else {
            tasks = taskService.findAll();
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("filter", filter);
        return "main";
    }



}
