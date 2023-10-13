package com.theycallmewolf.todolist.tasks;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;
    
    @PostMapping("/create")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        var userId = request.getAttribute("userId");
        taskModel.setUserId((UUID) userId);

        var task = this.taskRepository.save(taskModel);
        return task;
    }

}
