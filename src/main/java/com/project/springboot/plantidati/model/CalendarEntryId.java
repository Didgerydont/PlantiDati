package com.project.springboot.plantidati.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CalendarEntryId implements Serializable {
    private String calendar;
    private String entryId;

    // default constructor

    // equals() and hashCode() methods
}
