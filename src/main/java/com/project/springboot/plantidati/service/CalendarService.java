package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.model.Variety;

public interface CalendarService {
    Calendar createCalendar(User user, String name, String location, Variety variety);
}
