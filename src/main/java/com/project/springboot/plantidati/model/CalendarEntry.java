package com.project.springboot.plantidati.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "calendar_entry")
public class CalendarEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private int entryId;

    @ManyToOne
    @JoinColumn(name = "calendar_id", referencedColumnName = "calendar_Id", nullable = false)
    @JsonBackReference(value = "calendar-entry")
    private Calendar calendar;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "day_temp")
    private Integer dayTemp;

    @Column(name = "night_temp")
    private Integer nightTemp;

    @Column(name = "water_amount")
    private Float waterAmount;

    @Column(name = "nutrient_amount")
    private Float nutrientAmount;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @Column(name = "growth_stage")
    private Integer growthStage;

    @Column(name = "pest_issues")
    private boolean pestIssues;

    @Column(name = "pest_impact")
    private int pestImpact;

    @Column(name = "disease_issues")
    private boolean diseaseIssues;

    @Column(name = "disease_impact")
    private int diseaseImpact;

    @Column(name = "comment")
    private String comment;

    @Column(name = "light_amount")
    private int lightAmount;

    @Column(name = "harvested")
    private boolean harvested;

    @Column(name = "yield")
    private Float yield;

    @Column(name = "image")
    private Byte[] image;

    @OneToMany(mappedBy = "entry")
    @JsonManagedReference(value = "entry-comment")
    private List<CalendarComment> comments;

    @ManyToOne
    @JoinColumn(name = "variety_id", referencedColumnName = "variety_id", nullable = false)
    @JsonBackReference(value = "variety-entry")
    private Variety variety;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEntry that = (CalendarEntry) o;
        return entryId == that.entryId &&
                Objects.equals(calendar, that.calendar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, calendar);
    }
}
