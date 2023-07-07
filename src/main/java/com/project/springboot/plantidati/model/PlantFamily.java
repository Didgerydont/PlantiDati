package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PlantFamily")
public class PlantFamily {
    // Vars
    @Id
    private String plantFamilyId;
    @Column(nullable = false)
    private String familyName;


    // Getters && Setters
    public String getPlantFamilyId() {
        return plantFamilyId;
    }

    public void setPlantFamilyId(String plantFamilyId) {
        this.plantFamilyId = plantFamilyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

}