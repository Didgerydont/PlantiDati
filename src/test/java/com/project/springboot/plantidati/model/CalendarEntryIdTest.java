package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalendarEntryIdTest {

    private CalendarEntryId calendarEntryId1;
    private CalendarEntryId calendarEntryId2;
    private CalendarEntryId calendarEntryId3;

    @BeforeEach
    public void setUp() {
        calendarEntryId1 = new CalendarEntryId();
        calendarEntryId1.setCalendarId("cal1");
        calendarEntryId1.setEntryId("entry1");

        calendarEntryId2 = new CalendarEntryId();
        calendarEntryId2.setCalendarId("cal2");
        calendarEntryId2.setEntryId("entry2");

        calendarEntryId3 = new CalendarEntryId();
        calendarEntryId3.setCalendarId("cal1");
        calendarEntryId3.setEntryId("entry1");
    }

    @Test
    public void testGetCalendarId() {
        assertEquals("cal1", calendarEntryId1.getCalendarId());
        assertEquals("cal2", calendarEntryId2.getCalendarId());
    }

    @Test
    public void testGetEntryId() {
        assertEquals("entry1", calendarEntryId1.getEntryId());
        assertEquals("entry2", calendarEntryId2.getEntryId());
    }

    @Test
    public void testEquals() {
        assertNotEquals(calendarEntryId1, calendarEntryId2);
        assertEquals(calendarEntryId1, calendarEntryId3);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(calendarEntryId1.hashCode(), calendarEntryId2.hashCode());
        assertEquals(calendarEntryId1.hashCode(), calendarEntryId3.hashCode());
    }
}
