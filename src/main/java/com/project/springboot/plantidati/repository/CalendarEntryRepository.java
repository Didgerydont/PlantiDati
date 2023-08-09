package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntry, Integer> {
    List<CalendarEntry> findByCalendar_CalendarId(int calendarId);


}