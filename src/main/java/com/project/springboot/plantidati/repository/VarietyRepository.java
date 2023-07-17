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
            "v.variety_height = (SELECT AVG(c.height) FROM CalendarEntry c WHERE c.calendar_Id IN (SELECT cal.calendar_Id FROM Calendar cal WHERE cal.variety_Id = v.id)), " +
            "v.variety_width = (SELECT AVG(c.width) FROM CalendarEntry c WHERE c.calendar_Id IN (SELECT cal.calendar_Id FROM Calendar cal WHERE cal.variety_Id = v.id)), " +
            "v.variety_yield = (SELECT AVG(c.yield) FROM CalendarEntry c WHERE c.calendar_Id IN (SELECT cal.calendar_Id FROM Calendar cal WHERE cal.variety_Id = v.id)), " +
            "v.growth_rate = (SELECT AVG(c.growth_stage) FROM CalendarEntry c WHERE c.calendar_Id IN (SELECT cal.calendar_Id FROM Calendar cal WHERE cal.variety_Id = v.id)), " +
            "v.watering_requirement = (SELECT AVG(c.water_amount) FROM CalendarEntry c WHERE c.calendar_Id IN (SELECT cal.calendar_Id FROM Calendar cal WHERE cal.variety_Id = v.id)), " +
            "v.fertilizing_requirement = (SELECT AVG(c.nutrient_amount) FROM CalendarEntry c WHERE c.calendar_Id IN (SELECT cal.calendar_Id FROM Calendar cal WHERE cal.variety_Id = v.id)) " +
            "WHERE v.id IN (SELECT cal.variety_Id FROM Calendar cal)", nativeQuery = true)
    void updateVarietyAverages();

}