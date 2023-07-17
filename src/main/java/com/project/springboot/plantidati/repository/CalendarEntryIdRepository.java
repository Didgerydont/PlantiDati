package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CalendarEntryIdRepository extends JpaRepository<CalendarEntry, Integer> {
}
