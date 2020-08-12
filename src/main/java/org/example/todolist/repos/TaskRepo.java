package org.example.todolist.repos;

import org.example.todolist.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task,Long> {
    List<Task> findByTag(String tag);
}
