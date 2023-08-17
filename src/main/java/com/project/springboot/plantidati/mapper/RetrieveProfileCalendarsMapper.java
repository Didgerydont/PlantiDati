package com.project.springboot.plantidati.mapper;

import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.dto.RetrieveProfileCalendarsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetrieveProfileCalendarsMapper {

    @Mapping(target = "varietyDescription", source = "variety.varietyDescription")
    @Mapping(target = "plantName", source = "variety.plant.plantName")
    @Mapping(target = "varietyName", source = "variety.varietyName")
    RetrieveProfileCalendarsDTO toDTO(Calendar calendar);
}
