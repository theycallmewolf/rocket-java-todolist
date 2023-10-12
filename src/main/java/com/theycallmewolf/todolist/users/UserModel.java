package com.theycallmewolf.todolist.users;

public class UserModel {
   private String username;
   private String name;
   private String password;

   // getters and setters for username, name, and password
   public void setUsername(String username) {
       this.username = username;
   }

   public String getUsername() {
       return this.username;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getName() {
       return this.name;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   public String getPassword() {
       return this.password;
   }
}
