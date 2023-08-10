package com.project.springboot.plantidati.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantDTO {

    private int plantId;

    private String plantName;

    private PlantFamilyDTO plantFamily;

    private List<VarietyDTO> varieties;

}
