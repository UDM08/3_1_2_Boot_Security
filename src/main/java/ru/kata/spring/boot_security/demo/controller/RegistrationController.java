package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

@Controller
@RequestMapping("/")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("auth/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "registration_user";
    }

    @PostMapping("auth/registration")
    public String performRegistration(@ModelAttribute("user") User user) {
        registrationService.register(user);
        return "redirect:/login";
    }
}
