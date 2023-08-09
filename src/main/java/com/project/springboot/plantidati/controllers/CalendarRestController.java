package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.exception.EntityNotFoundException;
import com.project.springboot.plantidati.exception.UserNotFoundException;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.model.dto.CreateCalendarRequest;
import com.project.springboot.plantidati.service.CalendarService;
import com.project.springboot.plantidati.service.UserService;
import com.project.springboot.plantidati.service.VarietyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private VarietyService varietyService;

    @PostMapping("/create")
    public void createCalendar(@RequestBody CreateCalendarRequest request) {
        User user = userService.findUserById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + request.getUserId() + " not found"));
        Variety variety = varietyService.findById(request.getVarietyId())
                .orElseThrow(() -> new EntityNotFoundException("Variety with id " + request.getVarietyId() + " not found"));
        calendarService.createCalendar(user, request.getTitle(), request.getLocation(), variety);
    }


}
