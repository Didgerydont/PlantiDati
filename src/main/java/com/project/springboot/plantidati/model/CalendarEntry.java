package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CalendarEntry")
@IdClass(CalendarEntryId.class)
public class CalendarEntry implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "calendarId", nullable = false)
    private Calendar calendar;

    @Id
    @Column(name = "entryId")
    private String entryId;

    @Column(name = "date", nullable = false)
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

    @Column(name = "comment")
    private String comment;

    @Column(name = "image")
    private Byte[] image;

    @OneToMany(mappedBy = "entry")
    private List<CalendarComment> comments;



    // Getter and setter methods here...


    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(Integer dayTemp) {
        this.dayTemp = dayTemp;
    }

    public Integer getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(Integer nightTemp) {
        this.nightTemp = nightTemp;
    }

    public Float getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(Float waterAmount) {
        this.waterAmount = waterAmount;
    }

    public Float getNutrientAmount() {
        return nutrientAmount;
    }

    public void setNutrientAmount(Float nutrientAmount) {
        this.nutrientAmount = nutrientAmount;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Float getYield() {
        return yield;
    }

    public void setYield(Float yield) {
        this.yield = yield;
    }

    public Integer getGrowthStage() {
        return growthStage;
    }

    public void setGrowthStage(Integer growthStage) {
        this.growthStage = growthStage;
    }

    public String getPestIssues() {
        return pestIssues;
    }

    public void setPestIssues(String pestIssues) {
        this.pestIssues = pestIssues;
    }

    public String getDiseaseIssues() {
        return diseaseIssues;
    }

    public void setDiseaseIssues(String diseaseIssues) {
        this.diseaseIssues = diseaseIssues;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public List<CalendarComment> getComments() {
        return comments;
    }

    public void setComments(List<CalendarComment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEntry that = (CalendarEntry) o;
        return Objects.equals(calendar, that.calendar) &&
                Objects.equals(entryId, that.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendar, entryId);
    }
}
