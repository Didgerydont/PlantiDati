package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.dto.CalendarEntryDTO;

import java.util.List;

public interface CalendarEntryService {
    CalendarEntryDTO createCalendarEntryForCalendar(int calendarId, CalendarEntryDTO calendarEntryDTO);

    CalendarEntryDTO updateCalendarEntryForCalendar(int calendarId, int entryId, CalendarEntryDTO calendarEntryDTO);

    List<CalendarEntryDTO> returnFullCalendar(int calendarId);


}
