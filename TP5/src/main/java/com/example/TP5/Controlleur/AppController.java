package com.example.TP5.Controlleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/login")
    public String login() {
        return "login";  // Retourner la vue login.html
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";  // Retourner la vue admin.html
    }
}

