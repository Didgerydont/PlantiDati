package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.exception.EntityNotFoundException;
import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.model.dto.CreateCalendarRequest;
import com.project.springboot.plantidati.service.CalendarService;
import com.project.springboot.plantidati.service.PlantService;
import com.project.springboot.plantidati.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalendarRestControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private CalendarService calendarService;

    @Mock
    private PlantService plantService;

    @InjectMocks
    private CalendarRestController controller;


    @Test
    public void createCalendar_validRequest_createsCalendar() {
        // Arrange
        when(userService.findUserById(1)).thenReturn(Optional.of(new User()));
        when(plantService.findById(1)).thenReturn(Optional.of(new Plant()));

        CreateCalendarRequest request = new CreateCalendarRequest();
        request.setUserId(1);
        request.setVarietyId(1);
        request.setTitle("Test Title");
        request.setLocation("Test Location");

        // Act
        controller.createCalendar(request);

        // Assert
        verify(calendarService, times(1)).createCalendar(any(User.class), eq("Sample Title"), eq("Sample Location"), any(Variety.class));
    }

    @Test
    public void createCalendar_userNotFound_throwsException() {
        // Arrange
        when(userService.findUserById(1)).thenReturn(Optional.empty());

        CreateCalendarRequest request = new CreateCalendarRequest();
        request.setUserId(1);
        request.setVarietyId(1);

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> controller.createCalendar(request));
        assertEquals("User with id 1 not found", exception.getMessage());
    }

    @Test
    public void createCalendar_plantNotFound_throwsException() {
        // Arrange
        when(userService.findUserById(1)).thenReturn(Optional.of(new User()));
        when(plantService.findById(1)).thenReturn(Optional.empty());

        CreateCalendarRequest request = new CreateCalendarRequest();
        request.setUserId(1);
        request.setVarietyId(1);

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> controller.createCalendar(request));
        assertEquals("Plant with id 1 not found", exception.getMessage());
    }


}
