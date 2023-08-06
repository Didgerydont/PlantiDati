package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTest {

    private Calendar calendar;
    private User user;
    private Plant plant;

    @BeforeEach
    public void setUp() {
        calendar = new Calendar();
        user = new User();
        plant = new Plant();

    }

    @Test
    public void testGetCalendarId() {
        calendar.setCalendarId(1);
        assertEquals(1, calendar.getCalendarId());
    }

    @Test
    public void testGetUser() {
        calendar.setUser(user);
        assertEquals(user, calendar.getUser());
    }

    @Test
    public void testGetPlant() {
        calendar.setPlant(plant);
        assertEquals(plant, calendar.getPlant());
    }
}