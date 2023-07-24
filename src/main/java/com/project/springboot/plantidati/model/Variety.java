package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Variety")
public class Variety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variety_Id")
    private int varietyId;

    @ManyToOne
    @JoinColumn(name="plant_Id", nullable=false)
    private Plant plant;

    @Column(name = "variety_name")
    private String varietyName;

    @Column(name = "variety_description")
    private String varietyDescription;

    @Column(name = "variety_height")
    private Integer varietyHeight;

    @Column(name = "variety_width")
    private Integer varietyWidth;

    @Column(name = "variety_yield")
    private Float varietyYield;

    @Column(name = "growth_rate")
    private double growthRate;

    @Column(name = "watering_requirement")
    private Integer wateringRequirement;

    @Column(name = "fertilizing_requirement")
    private Integer fertilizingRequirement;

    @Column(name = "light_requirement")
    private Integer lightRequirement;

    @Column(name = "pest_resistance")
    private Integer pestResistance;

    @Column(name = "disease_resistance")
    private Integer diseaseResistance;

    @Column(name = "germination_phase")
    private Integer germinationPhase;

    @Column(name = "vegetative_stage")
    private Integer vegetativeStage;

    @Column(name = "budding_stage")
    private Integer buddingStage;

    @Column(name = "flowering_stage")
    private Integer floweringStage;

    @Column(name = "ripening_stage")
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