package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserService userService;
    private final PlantService plantService;

    @Autowired
    public CalendarServiceImpl(CalendarRepository calendarRepository, UserService userService, PlantService plantService) {
        this.calendarRepository = calendarRepository;
        this.userService = userService;
        this.plantService = plantService;
    }

    @Override
    public Calendar createCalendar(User user, String title, String location, Variety variety) {
        Calendar calendar = new Calendar();
        calendar.setUser(user);
        calendar.setTitle(title);
        calendar.setLocation(location);
        calendar.setVariety(variety);

        return calendarRepository.save(calendar);
    }

    @Override
    public List<Calendar> getAllCalendarsByUserId(int userId) {
        return calendarRepository.findByUserId(userId);
    }


}