package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_Id")
    private int plantId;

    @Column(name = "plant_name", nullable = false)
    private String plantName;

    @ManyToOne
    @JoinColumn(name = "plant_family_Id", nullable = false)
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
