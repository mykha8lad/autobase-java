package com.autobase.autobaseapp.controller;

import com.autobase.autobaseapp.model.User;
import com.autobase.autobaseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Главная страница (меню)
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Страница входа
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Обработка входа
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            model.addAttribute("user", user);
            return "welcome";
        }
        model.addAttribute("error", "Неверный логин или пароль");
        return "login";
    }

    // Страница регистрации
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Обработка регистрации
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role,
                           Model model) {
        if (userService.register(username, password, role)) {
            return "redirect:/login";
        }
        model.addAttribute("error", "Логин уже занят");
        return "register";
    }
}