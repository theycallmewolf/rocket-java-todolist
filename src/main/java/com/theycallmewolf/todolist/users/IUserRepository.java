package com.theycallmewolf.todolist.users;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// only represents the interface, not the implementation
// this is the contract that the implementation must follow
// <UserModel, UUID> UserModel is the entity, UUID is the primary key
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
    
}
