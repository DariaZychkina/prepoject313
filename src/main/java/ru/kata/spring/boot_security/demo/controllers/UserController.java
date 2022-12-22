package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String userAccount(Principal principal, Model model) {
        User user = userService.getUserByName(principal.getName());
        if (user.getRoles().stream().allMatch(role -> role.getRole().equals("ROLE_ADMIN"))) {
            model.addAttribute("users", userService.getUsersList());
            return "/adminAccount";
        }
        else {
            model.addAttribute("user", user);
            return "/userAccount";
        }
    }
}
