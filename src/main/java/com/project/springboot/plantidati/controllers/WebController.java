package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    // logged in user
    @GetMapping("/")
    public String activeUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        return "index";
    }

    // log in
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/registrationpage")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registrationpage";
    }

    @GetMapping("/registrationsuccess")
    public String registrationSuccess() {
        return "registrationSuccess";
    }


//    @GetMapping(value = "/logout")
//    public String logout() {
//        return "logout";
//    }

    @RequestMapping(value = "/profile")
    public String profile() {
        return "profile";
    }
}