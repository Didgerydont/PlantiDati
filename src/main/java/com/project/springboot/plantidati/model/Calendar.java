package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calendarId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private RegisteredUser user;

    @ManyToOne
    @JoinColumn(name = "varietyId", nullable = false)
    private Variety variety;

    // OneToMany annotation, defining a one-to-many relationship with the CalendarEntry entity
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalendarEntry> entries;

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public Variety getVariety() {
        return variety;
    }

    public void setVariety(Variety variety) {
        this.variety = variety;
    }

    public List<CalendarEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<CalendarEntry> entries) {
        this.entries = entries;
    }
}
