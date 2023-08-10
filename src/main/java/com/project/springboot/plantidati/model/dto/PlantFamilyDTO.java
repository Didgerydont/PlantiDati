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
public class PlantFamilyDTO {

    private int familyId;
    private String familyName;
    private List<PlantDTO> plants;

}
