package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.dto.CalendarEntryDTO;
import com.project.springboot.plantidati.service.CalendarEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendarEntry")
public class CalendarEntryRestController {

    @Autowired
    private CalendarEntryService calendarEntryService;


    // Add a new entry to a specific calendar.
    @PostMapping("/calendar/{calendarId}/entry")
    public ResponseEntity<CalendarEntryDTO> addEntryToCalendar(@PathVariable int calendarId, @RequestBody CalendarEntryDTO calendarEntryDTO) {
        CalendarEntryDTO savedEntry = calendarEntryService.createCalendarEntryForCalendar(calendarId, calendarEntryDTO);
        return ResponseEntity.ok(savedEntry);
    }


    // Retrieve all entries for a specific calendar.
    @GetMapping("/calendar/{calendarId}/entries")
    public ResponseEntity<List<CalendarEntryDTO>> getEntriesForCalendar(@PathVariable int calendarId) {
        List<CalendarEntryDTO> entries = calendarEntryService.returnFullCalendar(calendarId);
        return ResponseEntity.ok(entries);
    }

    //Update a specific entry for a calendar.
    @PutMapping("/calendar/{calendarId}/entry/{entryId}")
    public ResponseEntity<CalendarEntryDTO> updateEntryForCalendar(@PathVariable int calendarId, @PathVariable int entryId, @RequestBody CalendarEntryDTO calendarEntryDTO) {
        CalendarEntryDTO updatedEntry = calendarEntryService.updateCalendarEntryForCalendar(calendarId, entryId, calendarEntryDTO);
        return ResponseEntity.ok(updatedEntry);
    }

}
