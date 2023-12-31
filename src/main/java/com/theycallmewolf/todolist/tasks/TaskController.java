package com.theycallmewolf.todolist.tasks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theycallmewolf.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;
    
    // create a task
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        var userId = request.getAttribute("userId");
        taskModel.setUserId((UUID) userId);

        var currentDate = LocalDateTime.now();

        // Check if start date is in the past
        if(currentDate.isAfter(taskModel.getStartDate()) || currentDate.isAfter(taskModel.getEndDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Start and/or end date(s) must be in the future");
        }

        // Check if end date is before start date
        if(taskModel.getEndDate().isBefore(taskModel.getStartDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("End date must be after start date");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    // get all tasks
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var tasks = this.taskRepository.findByUserId((UUID) userId);
        return tasks;
    }

   
    // update a task
    // http://localhost:8080/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        var userId = request.getAttribute("userId");
        var task = this.taskRepository.findById(id).orElse(null);

        if(task == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Task not found");
        }
        
        if(!task.getUserId().equals(userId)) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("User is not authorized to update this task");
        }

        Utils.copyNonNullProperties(taskModel, task);
        
        var updatedTask = this.taskRepository.save(task);
        
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }
}
