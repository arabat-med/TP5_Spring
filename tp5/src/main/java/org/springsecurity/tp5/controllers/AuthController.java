package org.springsecurity.tp5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Retourne la vue login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Retourne la vue register.html
    }



}
