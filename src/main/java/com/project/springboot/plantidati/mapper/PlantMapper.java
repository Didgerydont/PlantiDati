package com.project.springboot.plantidati.mapper;

import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.model.dto.PlantDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantMapper {
    PlantDTO toDTO(Plant plant);

    Plant toEntity(PlantDTO plantDTO);
}

