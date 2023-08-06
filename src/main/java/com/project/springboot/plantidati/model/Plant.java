package com.project.springboot.plantidati.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @JoinColumn(name = "plant_family_id", nullable = false)
    @JsonBackReference
    private PlantFamily plantFamily;

}