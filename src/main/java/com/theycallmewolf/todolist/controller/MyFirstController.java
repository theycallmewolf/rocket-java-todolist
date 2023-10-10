package com.theycallmewolf.todolist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

// @Controller // page or template use cases
@RestController // for API use cases
@RequestMapping("/api/v1/first-route") // http://localhost:8080/api/v1/hello/

public class MyFirstController {

    // GET | POST | PUT | DELETE | PATCH
    @RequestMapping("/first-method")
    public String firstMessage() {
        return "Hello Wolf";
    }
}
