package com.project.springboot.plantidati.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VarietyDTO {

    private int varietyId;

    @NotNull
    @Size(min = 1, max = 50)
    private String varietyName;

    @NotNull
    @Size(min = 1, max = 400)
    private String varietyDescription;


    private Integer varietyHeight;


    private Integer varietyWidth;


    private Float varietyYield;

    @Size(min = 1, max = 10)
    private double growthRate;

    @Size(min = 1, max = 10)
    private Integer wateringRequirement;

    @Size(min = 1, max = 10)
    private Integer fertilizingRequirement;

    @Size(min = 1, max = 10)
    private Integer lightRequirement;

    @Size(min = 1, max = 10)
    private Integer pestResistance;

    private Integer diseaseResistance;

    private Integer germinationPhase;

    private Integer vegetativeStage;

    private Integer buddingStage;

    private Integer floweringStage;

    private Integer ripeningStage;


}
