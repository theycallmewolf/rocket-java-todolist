package com.theycallmewolf.todolist.tasks;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // generates getters and setters
@Entity(name = "tb_tasks") // maps this class to the `tb_tasks` table
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50)
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

     @Column(length = 10)
    private String priority;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID userId;

    public void setTitle(String title) throws Exception {
        if(title.length() > 50) {
            throw new Exception("Title must be less than 50 characters");
        }

        this.title = title;
    }
}
