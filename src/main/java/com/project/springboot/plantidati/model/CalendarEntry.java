package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CalendarEntry")
public class CalendarEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_Id")
    private int entryId;

    @ManyToOne
    @JoinColumn(name = "calendar_Id", referencedColumnName = "calendar_Id", nullable = false)
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
    private List<CalendarComment> comments;


    // Getter and setter methods

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
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

    public int getPestImpact() {
        return pestImpact;
    }

    public void setPestImpact(int pestImpact) {
        this.pestImpact = pestImpact;
    }

    public int getDiseaseImpact() {
        return diseaseImpact;
    }

    public void setDiseaseImpact(int diseaseImpact) {
        this.diseaseImpact = diseaseImpact;
    }

    public int getLightAmount() {
        return lightAmount;
    }

    public void setLightAmount(int lightAmount) {
        this.lightAmount = lightAmount;
    }

    public boolean isHarvested() {
        return harvested;
    }

    public void setHarvested(boolean harvested) {
        this.harvested = harvested;
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

    public Boolean getPestIssues() {
        return pestIssues;
    }

    public void setPestIssues(Boolean pestIssues) {
        this.pestIssues = pestIssues;
    }

    public Boolean getDiseaseIssues() {
        return diseaseIssues;
    }

    public void setDiseaseIssues(Boolean diseaseIssues) {
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
        return entryId == that.entryId &&
                Objects.equals(calendar, that.calendar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, calendar);
    }
}
