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
@Table(name = "variety")
public class Variety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variety_id")
    private int varietyId;

    @ManyToOne
    @JoinColumn(name = "plant_id", nullable = false)
    @JsonBackReference
    private Plant plant;

    @OneToMany(mappedBy = "variety")
    @JsonManagedReference
    private List<Calendar> calendars;

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


}