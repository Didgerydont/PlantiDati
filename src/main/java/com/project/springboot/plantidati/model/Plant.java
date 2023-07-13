package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Plant")
public class Plant {
    // Vars
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plantId;

    @Column(nullable = false)
    private String plantName;

    @ManyToOne
    @JoinColumn(name = "familyId", nullable = false)
    private PlantFamily plantFamily;

    // Getters && Setters
    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public PlantFamily getPlantFamily() {
        return plantFamily;
    }

    public void setPlantFamily(PlantFamily plantFamily) {
        this.plantFamily = plantFamily;
    }
}
