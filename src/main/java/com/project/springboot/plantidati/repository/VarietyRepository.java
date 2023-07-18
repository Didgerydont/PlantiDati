package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.Variety;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VarietyRepository extends JpaRepository<Variety, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Variety v SET " +
            "v.varietyHeight = (SELECT AVG(c.height) FROM CalendarEntry c WHERE c.calendar.calendarId IN (SELECT cal.calendarId FROM Calendar cal WHERE cal.variety.varietyId = v.varietyId)), " +
            "v.varietyWidth = (SELECT AVG(c.width) FROM CalendarEntry c WHERE c.calendar.calendarId IN (SELECT cal.calendarId FROM Calendar cal WHERE cal.variety.varietyId = v.varietyId)), " +
            "v.varietyYield = (SELECT AVG(c.yield) FROM CalendarEntry c WHERE c.calendar.calendarId IN (SELECT cal.calendarId FROM Calendar cal WHERE cal.variety.varietyId = v.varietyId)), " +
            "v.growthRate = (SELECT AVG(c.growthStage) FROM CalendarEntry c WHERE c.calendar.calendarId IN (SELECT cal.calendarId FROM Calendar cal WHERE cal.variety.varietyId = v.varietyId)), " +
            "v.wateringRequirement = (SELECT AVG(c.waterAmount) FROM CalendarEntry c WHERE c.calendar.calendarId IN (SELECT cal.calendarId FROM Calendar cal WHERE cal.variety.varietyId = v.varietyId)), " +
            "v.fertilizingRequirement = (SELECT AVG(c.nutrientAmount) FROM CalendarEntry c WHERE c.calendar.calendarId IN (SELECT cal.calendarId FROM Calendar cal WHERE cal.variety.varietyId = v.varietyId)) " +
            "WHERE v.varietyId IN (SELECT cal.variety.varietyId FROM Calendar cal)", nativeQuery = true)
    void updateVarietyAverages();


}