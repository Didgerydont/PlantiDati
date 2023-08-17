package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.controllers.dto.CreateCalendarRequest;
import com.project.springboot.plantidati.exception.EntityNotFoundException;
import com.project.springboot.plantidati.exception.UserNotFoundException;
import com.project.springboot.plantidati.mapper.RetrieveProfileCalendarsMapper;
import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.model.dto.RetrieveProfileCalendarsDTO;
import com.project.springboot.plantidati.service.CalendarService;
import com.project.springboot.plantidati.service.UserService;
import com.project.springboot.plantidati.service.VarietyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarRestController {

    private final UserService userService;
    private final CalendarService calendarService;
    private final VarietyService varietyService;
    private final RetrieveProfileCalendarsMapper retrieveProfileCalendarsMapper;

    private static final Logger logger = LoggerFactory.getLogger(CalendarRestController.class);

    @PostMapping("/create")
    public ResponseEntity<Void> createCalendar(@Valid @RequestBody CreateCalendarRequest request) {
        User user = userService.findUserById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + request.getUserId() + " not found"));
        Variety variety = varietyService.findById(request.getVarietyId())
                .orElseThrow(() -> new EntityNotFoundException("Variety with id " + request.getVarietyId() + " not found"));
        calendarService.createCalendar(user, request.getTitle(), request.getLocation(), variety);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getUserCalendars/{userId}")
    public ResponseEntity<List<RetrieveProfileCalendarsDTO>> getUserCalendars(@PathVariable int userId) {
        Optional<User> userOptional = userService.findUserById(userId);

        if (userOptional.isEmpty()) {
            logger.warn("---User with id {} not found", userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("+++User with id {} found", userId);
        }

        logger.info("Fetch Calendars for userId: {}", userId);
        List<Calendar> calendars = calendarService.getAllCalendarsByUserId(userId);

        if (calendars.isEmpty()) {
            logger.warn("---No calendars found for user with id {}", userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Fetched calendars: {}", calendars);

        // Convert the list of Calendar entities to a list of CalendarDTOs
        List<RetrieveProfileCalendarsDTO> retrieveProfileCalendarsDTOS = calendars.stream()
                .map(retrieveProfileCalendarsMapper::toDTO)
                .collect(Collectors.toList());

        logger.info("Converted calendars to DTOs: {}", retrieveProfileCalendarsDTOS);

        return new ResponseEntity<>(retrieveProfileCalendarsDTOS, HttpStatus.OK);
    }
}
