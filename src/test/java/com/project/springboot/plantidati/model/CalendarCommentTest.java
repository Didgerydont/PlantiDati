package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalendarCommentTest {

    private CalendarComment comment;
    private Calendar calendar;
    private CalendarEntry entry;
    private RegisteredUser user;

    @BeforeEach
    void setUp() {
        comment = new CalendarComment();
        calendar = new Calendar();
        entry = new CalendarEntry();
        user = new RegisteredUser();

        calendar.setCalendarId("calendarId1");
        entry.setEntryId("entryId1");
        user.setUserId("userId1");
    }

    @Test
    void testCommentId() {
        comment.setCommentId("commentId1");
        assertEquals("commentId1", comment.getCommentId());
    }

    @Test
    void testCalendar() {
        comment.setCalendar(calendar);
        assertEquals(calendar, comment.getCalendar());
    }

    @Test
    void testEntry() {
        comment.setEntry(entry);
        assertEquals(entry, comment.getEntry());
    }

    @Test
    void testUser() {
        comment.setUser(user);
        assertEquals(user, comment.getUser());
    }

    @Test
    void testCommentText() {
        comment.setComment("Test comment");
        assertEquals("Test comment", comment.getComment());
    }

    @Test
    void testDatePosted() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setDatePosted(timestamp);
        assertEquals(timestamp, comment.getDatePosted());
    }

    @Test
    void testEquals() {
        CalendarComment other = new CalendarComment();
        other.setCommentId("commentId1");
        other.setCalendar(calendar);
        other.setEntry(entry);
        other.setUser(user);

        assertTrue(comment.equals(other));
    }
}
