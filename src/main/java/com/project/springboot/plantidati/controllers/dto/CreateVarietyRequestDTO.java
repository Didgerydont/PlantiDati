package com.project.springboot.plantidati.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateVarietyRequestDTO {
    private String name;
    private String description;
    private int plantId;
}
