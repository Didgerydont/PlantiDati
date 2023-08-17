package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.repository.CalendarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalendarServiceImplTest {

    @Mock
    private CalendarRepository calendarRepository;

    @Mock
    private UserService userService;

    @Mock
    private PlantService plantService;

    @InjectMocks
    private CalendarServiceImpl calendarService;

    @Test
    void testCreateCalendar() {
        User user = new User();
        String title = "Test Title";
        String location = "Test Location";
        Variety variety = new Variety();

        Calendar calendar = new Calendar();
        calendar.setUser(user);
        calendar.setTitle(title);
        calendar.setLocation(location);
        calendar.setVariety(variety);

        when(calendarRepository.save(any(Calendar.class))).thenReturn(calendar);

        Calendar returnedCalendar = calendarService.createCalendar(user, title, location, variety);

        assertEquals(title, returnedCalendar.getTitle());
        assertEquals(location, returnedCalendar.getLocation());
    }

    @Test
    void testGetAllCalendarsByUserId() {
        int userId = 1;
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar();

        when(calendarRepository.findByUserId(userId)).thenReturn(List.of(calendar1, calendar2));

        List<Calendar> returnedCalendars = calendarService.getAllCalendarsByUserId(userId);

        assertEquals(2, returnedCalendars.size());
    }
}
