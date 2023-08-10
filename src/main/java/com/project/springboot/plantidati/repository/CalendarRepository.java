package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    @Query("SELECT c FROM Calendar c WHERE c.user.userId = :userId")
    List<Calendar> findByUserId(@Param("userId") int userId);

}