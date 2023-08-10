package com.project.springboot.plantidati.mapper;

import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.dto.CalendarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalendarMapper {

    CalendarDTO toDTO(Calendar calendar);


    Calendar toEntity(CalendarDTO calendarDTO);
}
