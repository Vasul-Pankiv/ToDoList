package org.example.todolist.controller;

import org.example.todolist.domain.Task;
import org.example.todolist.domain.User;
import org.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/task-list")
    public String addTask(@AuthenticationPrincipal User currentUser,
                          @Valid Task task,
                          BindingResult bindingResult,
                          Model model
    ) {
        task.setAuthor(currentUser);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("task", task);
        } else {
            taskService.save(task);
        }

        Iterable<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "main";

    }

    @GetMapping("/task-list/edit")
    public String getTask(
            @RequestParam Task task,
            Model model
    ) {

        model.addAttribute("task", task);
        return "taskEditor";
    }

    @PostMapping("/task-list/edit")
    public String updateTask(
            @RequestParam(required = false) User user,
            @Valid Task task,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("task", task);
            return "taskEditor";
        } else {
            taskService.updateTask(task, task.getTitle(), task.getTag(), task.getDescription());
            if (user != null) {
                return "redirect:/task-list?user=" + user.getId();
            }
            return "redirect:/task-list";
        }
    }

    @GetMapping("/task-list/delete")
    public String deleteTask(
            @RequestParam(required = false) User user,
            @RequestParam Task task
    ) {
        taskService.delete(task);
        if (user != null) {
            return "redirect:/task-list?user=" + user.getId();
        }
        return "redirect:/task-list";
    }

}
