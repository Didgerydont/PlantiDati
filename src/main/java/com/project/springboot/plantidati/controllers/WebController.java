package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.RegisteredUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/registrationpage")
    public String registrationPage(Model model) {
        model.addAttribute("user", new RegisteredUser());
        return "registrationpage";
    }

    @GetMapping("/registrationsuccess")
    public String registrationSuccess() {
        return "registrationSuccess";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "login";
    }


}