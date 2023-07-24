package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PlantFamily")
public class PlantFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_family_Id")
    private int familyId;

    @Column(name = "family_Name", nullable = false)
    private String familyName;

    @OneToMany(mappedBy = "plantFamily")
    private List<Plant> plants;

    // Getters && Setters
    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
