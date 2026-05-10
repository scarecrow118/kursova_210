package com.example.taxicompany.controller;


import com.example.taxicompany.entity.User;
import com.example.taxicompany.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegController {

    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute User user, Model model) {

        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "юзер вже є");
            return "register";
        }
        user.setRating("5");
        user.setEnabled(true);
        user.setRole("USER");
        userService.createUser(user);
        return "redirect:/account";
    }
}

