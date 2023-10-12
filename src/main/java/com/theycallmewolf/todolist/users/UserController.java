package com.theycallmewolf.todolist.users;

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
    public void createUser(@RequestBody UserModel userModel) {
        System.out.println(userModel.username);
        System.out.println(userModel.name);
        System.out.println(userModel.password);
    }
}
