package org.walletmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.walletmanagementsystem.entity.User;
import org.walletmanagementsystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/register")
    public String registerUser(@RequestParam String username) {
        User registeredUser = userService.register(username);
        return ("User " + registeredUser.getUsername() + " registered successfully.");
    }

}
