package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalendarEntryIdTest {

    private CalendarEntryId calendarEntryId1;
    private CalendarEntryId calendarEntryId2;

    @BeforeEach
    public void setUp() {
        calendarEntryId1 = new CalendarEntryId("Calendar1", "Entry1");
        calendarEntryId2 = new CalendarEntryId("Calendar2", "Entry2");
    }

    @Test
    public void testGetCalendar() {
        assertEquals("Calendar1", calendarEntryId1.getCalendar());
    }

    @Test
    public void testSetCalendar() {
        calendarEntryId1.setCalendar("Calendar3");
        assertEquals("Calendar3", calendarEntryId1.getCalendar());
    }

    @Test
    public void testGetEntryId() {
        assertEquals("Entry1", calendarEntryId1.getEntryId());
    }

    @Test
    public void testSetEntryId() {
        calendarEntryId1.setEntryId("Entry3");
        assertEquals("Entry3", calendarEntryId1.getEntryId());
    }

    @Test
    public void testEquals() {
        assertNotEquals(calendarEntryId1, calendarEntryId2);
        assertEquals(calendarEntryId1, new CalendarEntryId("Calendar1", "Entry1"));
    }

    @Test
    public void testHashCode() {
        assertNotEquals(calendarEntryId1.hashCode(), calendarEntryId2.hashCode());
        assertEquals(calendarEntryId1.hashCode(), new CalendarEntryId("Calendar1", "Entry1").hashCode());
    }
}
