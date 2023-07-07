package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

import java.sql.Date;

// The @Entity annotation specifies that the class is an entity and is mapped to a database table.
@Entity
// The @Table annotation specifies the name of the database table to be used for mapping.
@Table(name = "CalendarEntry")
// The @IdClass annotation specifies a composite primary key class that is mapped to multiple fields or properties of the entity.
@IdClass(CalendarEntryId.class)
public class CalendarEntry {

    // The @ManyToOne annotation is used to establish the Many-To-One relationship between CalendarEntry and Calendar entities.
    // The @Id annotation specifies the primary key of the entity.
    // The @JoinColumn annotation indicates the column for joining an entity association or element collection.
    @Id
    @ManyToOne
    @JoinColumn(name = "calendarId", nullable = false)
    private Calendar calendar;

    // The @Id annotation specifies the primary key of the entity.
    // The @Column annotation specifies the details of the column to which a field or property will be mapped.
    @Id
    @Column(name = "entryId")
    private String entryId;

    // Various @Column mappings for the fields in the CalendarEntry entity.
    @Column(name = "date")
    private Date date;

    @Column(name = "dayTemp")
    private Integer dayTemp;

    @Column(name = "nightTemp")
    private Integer nightTemp;

    @Column(name = "waterAmount")
    private Float waterAmount;

    @Column(name = "nutrientAmount")
    private Float nutrientAmount;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @Column(name = "yield")
    private Float yield;

    @Column(name = "growthStage")
    private Integer growthStage;

    @Column(name = "pestIssues")
    private String pestIssues;

    @Column(name = "diseaseIssues")
    private String diseaseIssues;

    @Column(name = "comments")
    private String comments;

    @Column(name = "image")
    private Byte[] image;

    // Getters
    public Calendar getCalendar() {
        return calendar;
    }

    public String getEntryId() {
        return entryId;
    }

    public Date getDate() {
        return date;
    }

    public Integer getDayTemp() {
        return dayTemp;
    }

    public Integer getNightTemp() {
        return nightTemp;
    }

    public Float getWaterAmount() {
        return waterAmount;
    }

    public Float getNutrientAmount() {
        return nutrientAmount;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Float getYield() {
        return yield;
    }

    public Integer getGrowthStage() {
        return growthStage;
    }

    public String getPestIssues() {
        return pestIssues;
    }

    public String getDiseaseIssues() {
        return diseaseIssues;
    }

    public String getComments() {
        return comments;
    }

    public Byte[] getImage() {
        return image;
    }

    // Setters
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDayTemp(Integer dayTemp) {
        this.dayTemp = dayTemp;
    }

    public void setNightTemp(Integer nightTemp) {
        this.nightTemp = nightTemp;
    }

    public void setWaterAmount(Float waterAmount) {
        this.waterAmount = waterAmount;
    }

    public void setNutrientAmount(Float nutrientAmount) {
        this.nutrientAmount = nutrientAmount;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setYield(Float yield) {
        this.yield = yield;
    }

    public void setGrowthStage(Integer growthStage) {
        this.growthStage = growthStage;
    }

    public void setPestIssues(String pestIssues) {
        this.pestIssues = pestIssues;
    }

    public void setDiseaseIssues(String diseaseIssues) {
        this.diseaseIssues = diseaseIssues;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

}