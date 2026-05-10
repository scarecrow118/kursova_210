package com.example.taxicompany.controller;

import com.example.taxicompany.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/redirect_login")
    public String enter(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/account";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String Logout(){
        return "redirect:login";
    }
}