package org.example.todolist.controller;

import org.example.todolist.domain.Task;
import org.example.todolist.domain.User;
import org.example.todolist.repos.TaskRepo;
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
    private TaskRepo taskRepo;
    @GetMapping("/")
    public String greeting(Model model) {

        return "greeting";
    }
    @GetMapping("/main")
    public String main(@RequestParam (required = false,defaultValue = "")String filter,Model model){
        Iterable<Task> tasks;
        if(filter != null && !filter.isEmpty()){
            tasks = taskRepo.findByTag(filter);
        }
        else{
            tasks = taskRepo.findAll();
        }
        model.addAttribute("tasks",tasks);
        model.addAttribute("filter",filter);
        return "main";
    }
    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Model model
    ){
        Task task = new Task(text, tag,user);
        taskRepo.save(task);

        Iterable<Task> tasks = taskRepo.findAll();
        model.addAttribute("tasks",tasks);
        return "main";
    }

}
