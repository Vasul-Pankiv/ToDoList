package org.example.todolist.service;

import org.example.todolist.domain.Task;
import org.example.todolist.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> findAll(){
        return taskRepo.findAll();
    }

    public List<Task> findByTag(String filter) {
        return taskRepo.findByTag(filter);
    }

    public void save(Task task) {
        taskRepo.save(task);
    }

    public void updateTask(Task task, String title, String tag, String description) {
        task.setTitle(title);
        task.setTag(tag);
        task.setDescription(description);
        taskRepo.save(task);
    }

    public void delete(Task task) {
        taskRepo.delete(task);
    }
}
