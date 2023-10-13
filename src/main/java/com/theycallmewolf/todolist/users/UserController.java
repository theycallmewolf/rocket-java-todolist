package com.theycallmewolf.todolist.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * modifiers: 
 * `public` - accessible from anywhere
 * `private` - accessible only from within the class
 * `protected` - accessible from within the class and subclasses
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // dependency injection
    private IUserRepository userRepository;

    /**
     * `String` - a sequence of characters
     * `Integer` (int) - whole numbers
     * `Float` (double) - decimal numbers
     * `Double` (double) - decimal numbers
     * `Boolean` (bool) - true or false
     * `Char` - a single character
     * `Date` - a date
     * `void` - nothing
     */
    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null) {
            System.out.println("User already exists");
            return null;
        }

        var createdUser = this.userRepository.save(userModel);
        return createdUser;
    }
}
