package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.exception.EntityNotFoundException;
import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.service.CalendarService;
import com.project.springboot.plantidati.service.PlantService;
import com.project.springboot.plantidati.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private PlantService plantService;

    @PostMapping("/create")
    public void createCalendar(@RequestParam("userId") int userId,
                               @RequestParam("title") String title,
                               @RequestParam("location") String location,
                               @RequestParam("plantId") int plantId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
        Plant plant = plantService.findById(plantId)
                .orElseThrow(() -> new EntityNotFoundException("Plant with id " + plantId + " not found"));
        calendarService.createCalendar(user, title, location, plant);
    }


}
