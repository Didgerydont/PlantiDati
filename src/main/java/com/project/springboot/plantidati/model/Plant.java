package com.project.springboot.plantidati.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private int plantId;

    @Column(name = "plant_name", nullable = false)
    private String plantName;

    @ManyToOne
    @JoinColumn(name = "plant_family_id", nullable = false)
    @JsonBackReference
    private PlantFamily plantFamily;

    // allow access to each plants varieties.
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Variety> varieties;

}