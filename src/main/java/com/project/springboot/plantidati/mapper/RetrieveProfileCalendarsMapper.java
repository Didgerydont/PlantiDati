package com.project.springboot.plantidati.mapper;

import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.dto.RetrieveProfileCalendarsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetrieveProfileCalendarsMapper {

    @Mapping(target = "varietyDescription", source = "varietyDescription")
    @Mapping(target = "plantName", source = "plantName")
    @Mapping(target = "varietyName", source = "varietyName")
    RetrieveProfileCalendarsDTO toDTO(Calendar calendar);
}
