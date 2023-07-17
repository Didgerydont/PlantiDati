package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

}