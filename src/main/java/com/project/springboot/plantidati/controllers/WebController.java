package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.service.AuthenticationService;
import com.project.springboot.plantidati.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class WebController {
    private final AuthenticationService authService;

    private final UserService userService;

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


//    @GetMapping(value = "/logout")
//    public String logout() {
//        return "logout";
//    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<User> existingUser = userService.findUserByUsername(username);
        if (existingUser.isPresent()) {
            model.addAttribute("user", existingUser.get());
            return "profile";
        } else {
            return "error";
        }
    }

    // Calendar Creation Form
    @GetMapping("/calendarCreate")
    public String calendarCreationForm(Model model) {

        return "calendarCreationform";
    }

    @GetMapping("calendarEntry")
    public String calendarEntry(Model model) {

        return "calendarEntry";
        
    }

}