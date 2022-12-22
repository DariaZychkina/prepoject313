package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder cryptPasswordEncoder;

    public AdminController(UserService userService, RoleService roleService, BCryptPasswordEncoder cryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @GetMapping(value = "/editUser/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "/CRUDOperations/editUser";
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/CRUDOperations/addUser";
    }

    @GetMapping(value="/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/adminAccount";
    }

    @PostMapping(value="/editUser/{getId}")
    public String saveEditUser(@PathVariable Long getId, @ModelAttribute("user") User user){
        user.setId(getId);
        userService.updateUser(user);
        return "redirect:/adminAccount";
    }

    @PostMapping(value="/addUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/adminAccount";
    }
    @GetMapping("/adminAccount")
    public String adminAcc(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "/adminAccount";
    }

}
