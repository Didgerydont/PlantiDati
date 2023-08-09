package com.project.springboot.plantidati.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "plant_family")
public class PlantFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_family_id")
    private int familyId;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    @OneToMany(mappedBy = "plantFamily")
    @JsonManagedReference
    private List<Plant> plants;


    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
