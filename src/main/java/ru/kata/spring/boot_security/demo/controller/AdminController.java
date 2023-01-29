package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")

public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String AllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "user_list";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String removeById(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";

    }

    @PatchMapping("edit/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user_edit";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user_id";
    }
}
