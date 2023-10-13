package com.theycallmewolf.todolist.users;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import lombok.Getter;
// import lombok.Setter;
import lombok.Data;

// @Getter
// @Setter
@Data
@Entity(name = "tb_users")
public class UserModel {
   
   @Id
   @GeneratedValue(generator = "UUID")
   private UUID id;

   // @Column(name = "user") table column name = "user", variable name = "username"
   @Column(unique = true)
   private String username;
   private String name;
   private String password;

   @CreationTimestamp
   private LocalDateTime createdAt;
}
