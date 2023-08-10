package com.project.springboot.plantidati.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private int calendarId;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variety_id", referencedColumnName = "variety_id")
    @JsonBackReference
    private Variety variety;


    // OneToMany annotation, defining a one-to-many relationship with the CalendarEntry entity
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CalendarEntry> entries;


    public List<CalendarEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<CalendarEntry> entries) {
        this.entries = entries;
    }


}
