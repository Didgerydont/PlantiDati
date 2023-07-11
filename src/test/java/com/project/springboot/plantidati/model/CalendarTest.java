package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTest {

    private Calendar calendar;
    private RegisteredUser user;
    private Variety variety;

    @BeforeEach
    public void setUp() {
        calendar = new Calendar();
        user = new RegisteredUser();
        variety = new Variety();
    }

    @Test
    public void testGetCalendarId() {
        calendar.setCalendarId("Calendar1");
        assertEquals("Calendar1", calendar.getCalendarId());
    }

    @Test
    public void testGetUser() {
        calendar.setUser(user);
        assertEquals(user, calendar.getUser());
    }

    @Test
    public void testGetVariety() {
        calendar.setVariety(variety);
        assertEquals(variety, calendar.getVariety());
    }
}