package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PlantFamily")
public class PlantFamily {
    // Vars
    @Id
    private int plantFamilyId;
    @Column(nullable = false)
    private String familyName;
    @Column(nullable = false)
    private String description;

    // Getters && Setters
    public int getPlantFamilyId() {
        return plantFamilyId;
    }

    public void setPlantFamilyId(int plantFamilyId) {
        this.plantFamilyId = plantFamilyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}