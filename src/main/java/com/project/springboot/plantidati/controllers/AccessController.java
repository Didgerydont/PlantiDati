package com.project.springboot.plantidati.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// The @Controller annotation indicates that this class serves as a web controller in the MVC pattern
@Controller
public class AccessController {

    // The @GetMapping("/**") annotation maps all HTTP GET requests to the defaultPage() method
    @GetMapping("/**")
    public ModelAndView defaultPage(@AuthenticationPrincipal UserDetails user) {
        // Create a ModelAndView object with "Index" as the view name
        ModelAndView nextPage = new ModelAndView("Index");

        // Add the authenticated user's details to the model.
        // The @AuthenticationPrincipal annotation allows you to access the current authenticated user.
        nextPage.addObject("user", user);

        // Return the ModelAndView object
        return nextPage;
    }
}
