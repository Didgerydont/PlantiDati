package com.project.springboot.plantidati.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {

    private int calendarId;
    @NotNull
    @Size(min = 1, max = 50)
    private String title;
    
    private String description;
    @NotNull
    @Size(min = 1, max = 50)
    private String location;

    private List<CalendarEntryDTO> entries;
    private VarietyDTO variety;

}
