package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
@Entity
@Table(name = "Calendar")
public class Calendar {

    @Id
    private String calendarId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private RegisteredUser user;

    @ManyToOne
    @JoinColumn(name = "varietyId", nullable = false)
    private Variety variety;

    // Getter and setter for calendarId
    public String getCalendarId() {
        return this.calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    // Getter and setter for user
    public RegisteredUser getUser() {
        return this.user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    // Getter and setter for variety
    public Variety getVariety() {
        return this.variety;
    }

    public void setVariety(Variety variety) {
        this.variety = variety;
    }
}