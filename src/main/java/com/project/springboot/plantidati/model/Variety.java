package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

// The @Entity annotation specifies that the class is an entity and is mapped to a database table.
@Entity
// The @Table annotation specifies the name of the database table to be used for mapping.
@Table(name = "Variety")
public class Variety {

    // The @Id annotation specifies the primary key of the entity.
    // The @Column annotation specifies the details of the column to which a field or property will be mapped.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int varietyId;

    // The @ManyToOne annotation is used to establish the Many-To-One relationship between Variety and PlantFamily entities.
    // The @JoinColumn annotation indicates the column for joining an entity association or element collection.
    @ManyToOne
    @JoinColumn(name="plantId", nullable=false)
    private Plant plant;

    // Various @Column mappings for the fields in the Variety entity.
    @Column(name = "varietyName")
    private String varietyName;

    @Column(name = "varietyDescription")
    private String varietyDescription;

    @Column(name = "varietyHeight")
    private Integer varietyHeight;

    @Column(name = "varietyWidth")
    private Integer varietyWidth;

    @Column(name = "varietyYield")
    private Float varietyYield;

    @Column(name = "growthRate")
    private double growthRate;

    @Column(name = "wateringRequirement")
    private Integer wateringRequirement;

    @Column(name = "fertilizingRequirement")
    private Integer fertilizingRequirement;

    @Column(name = "lightRequirement")
    private Integer lightRequirement;

    @Column(name = "pestResistance")
    private Integer pestResistance;

    @Column(name = "diseaseResistance")
    private Integer diseaseResistance;

    @Column(name = "germinationPhase")
    private Integer germinationPhase;

    @Column(name = "vegetativeStage")
    private Integer vegetativeStage;

    @Column(name = "buddingStage")
    private Integer buddingStage;

    @Column(name = "floweringStage")
    private Integer floweringStage;

    @Column(name = "ripeningStage")
    private Integer ripeningStage;

    // Getters
    public int getVarietyId() {
        return varietyId;
    }

    public Plant getPlant() {
        return plant;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public String getVarietyDescription() {
        return varietyDescription;
    }

    public Integer getVarietyHeight() {
        return varietyHeight;
    }

    public Integer getVarietyWidth() {
        return varietyWidth;
    }

    public Float getVarietyYield() {
        return varietyYield;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public Integer getWateringRequirement() {
        return wateringRequirement;
    }

    public Integer getFertilizingRequirement() {
        return fertilizingRequirement;
    }

    public Integer getLightRequirement() {
        return lightRequirement;
    }

    public Integer getPestResistance() {
        return pestResistance;
    }

    public Integer getDiseaseResistance() {
        return diseaseResistance;
    }

    public Integer getGerminationPhase() {
        return germinationPhase;
    }

    public Integer getVegetativeStage() {
        return vegetativeStage;
    }

    public Integer getBuddingStage() {
        return buddingStage;
    }

    public Integer getFloweringStage() {
        return floweringStage;
    }

    public Integer getRipeningStage() {
        return ripeningStage;
    }

    // Setters
    public void setVarietyId(int varietyId) {
        this.varietyId = varietyId;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public void setVarietyDescription(String varietyDescription) {
        this.varietyDescription = varietyDescription;
    }

    public void setVarietyHeight(Integer varietyHeight) {
        this.varietyHeight = varietyHeight;
    }

    public void setVarietyWidth(Integer varietyWidth) {
        this.varietyWidth = varietyWidth;
    }

    public void setVarietyYield(Float varietyYield) {
        this.varietyYield = varietyYield;
    }

    public void setGrowthRate(Integer growthRate) {
        this.growthRate = growthRate;
    }

    public void setWateringRequirement(Integer wateringRequirement) {
        this.wateringRequirement = wateringRequirement;
    }

    public void setFertilizingRequirement(Integer fertilizingRequirement) {
        this.fertilizingRequirement = fertilizingRequirement;
    }

    public void setLightRequirement(Integer lightRequirement) {
        this.lightRequirement = lightRequirement;
    }

    public void setPestResistance(Integer pestResistance) {
        this.pestResistance = pestResistance;
    }

    public void setDiseaseResistance(Integer diseaseResistance) {
        this.diseaseResistance = diseaseResistance;
    }

    public void setGerminationPhase(Integer germinationPhase) {
        this.germinationPhase = germinationPhase;
    }

    public void setVegetativeStage(Integer vegetativeStage) {
        this.vegetativeStage = vegetativeStage;
    }

    public void setBuddingStage(Integer buddingStage) {
        this.buddingStage = buddingStage;
    }

    public void setFloweringStage(Integer floweringStage) {
        this.floweringStage = floweringStage;
    }

    public void setRipeningStage(Integer ripeningStage) {
        this.ripeningStage = ripeningStage;
    }

}