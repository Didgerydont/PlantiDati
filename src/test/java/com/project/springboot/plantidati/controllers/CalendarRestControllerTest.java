package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.controllers.dto.CreateCalendarRequest;
import com.project.springboot.plantidati.mapper.RetrieveProfileCalendarsMapper;
import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.model.dto.RetrieveProfileCalendarsDTO;
import com.project.springboot.plantidati.service.CalendarService;
import com.project.springboot.plantidati.service.UserService;
import com.project.springboot.plantidati.service.VarietyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalendarRestControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private CalendarService calendarService;

    @Mock
    private VarietyService varietyService;

    @Mock
    private RetrieveProfileCalendarsMapper retrieveProfileCalendarsMapper;

    @InjectMocks
    private CalendarRestController calendarRestController;

    @Test
    void testCreateCalendar() {
        CreateCalendarRequest request = new CreateCalendarRequest();
        request.setUserId(1);
        request.setTitle("Test Title");
        request.setLocation("Test Location");
        request.setVarietyId(1);

        User user = new User();
        Variety variety = new Variety();

        when(userService.findUserById(request.getUserId())).thenReturn(Optional.of(user));
        when(varietyService.findById(request.getVarietyId())).thenReturn(Optional.of(variety));

        ResponseEntity<Void> response = calendarRestController.createCalendar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetUserCalendars_UserNotFound() {
        int userId = 1;

        when(userService.findUserById(userId)).thenReturn(Optional.empty());

        ResponseEntity<List<RetrieveProfileCalendarsDTO>> response = calendarRestController.getUserCalendars(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetUserCalendars_NoCalendars() {
        int userId = 1;
        User user = new User();

        when(userService.findUserById(userId)).thenReturn(Optional.of(user));
        when(calendarService.getAllCalendarsByUserId(userId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<RetrieveProfileCalendarsDTO>> response = calendarRestController.getUserCalendars(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetUserCalendars() {
        int userId = 1;
        User user = new User();
        Calendar calendar = new Calendar();
        RetrieveProfileCalendarsDTO retrieveProfileCalendarsDTO = new RetrieveProfileCalendarsDTO();

        when(userService.findUserById(userId)).thenReturn(Optional.of(user));
        when(calendarService.getAllCalendarsByUserId(userId)).thenReturn(Collections.singletonList(calendar));
        when(retrieveProfileCalendarsMapper.toDTO(calendar)).thenReturn(retrieveProfileCalendarsDTO);

        ResponseEntity<List<RetrieveProfileCalendarsDTO>> response = calendarRestController.getUserCalendars(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}
