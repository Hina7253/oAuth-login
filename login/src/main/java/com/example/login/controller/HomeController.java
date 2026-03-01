package com.example.login.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model,
                       @AuthenticationPrincipal OAuth2User user) {

        model.addAttribute("name",
                user.getAttribute("login"));

        return "home";
    }
}