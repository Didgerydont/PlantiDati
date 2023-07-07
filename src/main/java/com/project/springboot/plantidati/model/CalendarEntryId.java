package com.project.springboot.plantidati.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CalendarEntryId implements Serializable {
    private String calendar;
    private String entryId;

    // Default Constructor
    public CalendarEntryId() {
    }

    // Parameterized constructor
    public CalendarEntryId(String calendar, String entryId) {
        this.calendar = calendar;
        this.entryId = entryId;
    }

    // Getters and setters
    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEntryId that = (CalendarEntryId) o;
        return Objects.equals(calendar, that.calendar) && Objects.equals(entryId, that.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendar, entryId);
    }
}
