package com.example.systemmonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf!");
        return "index"; // Corresponds to src/main/resources/templates/index.html
    }
}
