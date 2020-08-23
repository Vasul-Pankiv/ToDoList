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

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/task-list")
    public String addTask(@AuthenticationPrincipal User currentUser,
                          @RequestParam User user,
                          @RequestParam Task task,
                          @RequestParam String title,
                          @RequestParam String tag,
                          @RequestParam String description,
                          Model model
    ) {
        if(task == null){
        Task newTask = new Task(title, description, tag, user);
        taskService.save(newTask);

        Iterable<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "main";
        }
        else{
            model.addAttribute("isCurrentUser", user.equals(currentUser));
            taskService.updateTask(task,title,tag,description);
            return "redirect:/task-list?user=" + user.getId();
        }
    }

    @GetMapping("/task-list/edit")
    public String getTask(
            @RequestParam Task task,
            Model model
    ){

        model.addAttribute("task",task);
       return "taskEditor";
    }

    @PostMapping("/task-list/edit")
    public String updateTask(
            @RequestParam(required = false) User user,
            @RequestParam Task task,
            @RequestParam String title,
            @RequestParam String tag,
            @RequestParam String description
    ){
        taskService.updateTask(task,title,tag,description);
        if(user != null){
            return "redirect:/task-list?user="+user.getId();
        }
        return "redirect:/task-list";
    }

    @GetMapping("/task-list/delete")
    public String deleteTask(
            @RequestParam(required = false) User user,
            @RequestParam Task task
    ){
        taskService.delete(task);
        if(user != null){
            return "redirect:/task-list?user="+user.getId();
        }
        return "redirect:/task-list";
    }

}
