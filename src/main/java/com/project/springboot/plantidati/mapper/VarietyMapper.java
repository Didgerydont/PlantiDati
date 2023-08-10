package com.project.springboot.plantidati.mapper;

import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.model.dto.VarietyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PlantMapper.class})
public interface VarietyMapper {
    VarietyDTO toDTO(Variety variety);

    Variety toEntity(VarietyDTO varietyDTO);
}

