package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.model.User;

public interface CalendarService {
    Calendar createCalendar(User user, String name, String location, Plant plant);
}
