package com.bit.partsstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("/user")
    public String userAccess() {
        return "Hello, User!";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Hello, Admin!";
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the public page!";
    }
}
