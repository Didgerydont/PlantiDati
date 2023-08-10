package com.project.springboot.plantidati.controllers;


import com.project.springboot.plantidati.controllers.dto.CreateCalendarRequest;
import com.project.springboot.plantidati.exception.EntityNotFoundException;
import com.project.springboot.plantidati.exception.UserNotFoundException;
import com.project.springboot.plantidati.mapper.CalendarMapper;
import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.model.dto.CalendarDTO;
import com.project.springboot.plantidati.service.CalendarService;
import com.project.springboot.plantidati.service.UserService;
import com.project.springboot.plantidati.service.VarietyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private CalendarMapper calendarMapper;

    private static final Logger logger = LoggerFactory.getLogger(CalendarRestController.class);

    @PostMapping("/create")
    public void createCalendar(@RequestBody CreateCalendarRequest request) {
        User user = userService.findUserById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + request.getUserId() + " not found"));
        Variety variety = varietyService.findById(request.getVarietyId())
                .orElseThrow(() -> new EntityNotFoundException("Variety with id " + request.getVarietyId() + " not found"));
        calendarService.createCalendar(user, request.getTitle(), request.getLocation(), variety);
    }

    @GetMapping("/getUserCalendars/{userId}")
    public ResponseEntity<List<CalendarDTO>> getUserCalendars(@PathVariable int userId) {
        Optional<User> userOptional = userService.findUserById(userId);


        if (userOptional.isEmpty()) {
            logger.warn("---User with id " + userId + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("+++User with id " + userId + " found");
        }

        logger.info("Fetch Calendars for userId:  " + userId);
        List<Calendar> calendars = calendarService.getAllCalendarsByUserId(userId);
        if (calendars == null || calendars.isEmpty()) {
            logger.warn("---No calendars found for user with id " + userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Fetched calendars: {}", calendars);

        // Convert the list of Calendar entities to a list of CalendarDTOs
        List<CalendarDTO> calendarDTOs = calendars.stream()
                .map(calendarMapper::toDTO)
                .collect(Collectors.toList());

        logger.info("Converted calendars to DTOs: {}", calendarDTOs);

        return new ResponseEntity<>(calendarDTOs, HttpStatus.OK);
    }


}
