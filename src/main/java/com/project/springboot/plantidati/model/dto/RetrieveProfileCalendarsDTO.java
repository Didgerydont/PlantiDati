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
public class RetrieveProfileCalendarsDTO {

    private int calendarId;
    private String title;
    private String varietyDescription;
    private String varietyName;
    private String plantName;
    private String location;
    private List<CalendarEntryDTO> entries;
    private VarietyDTO variety;


}
