package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalendarEntryTest {

    @Test
    void testCalendarEntry() {
        CalendarEntry entry = new CalendarEntry();

        // Mock data
        Calendar calendar = new Calendar(); // replace with a real instance
        int entryId = 1;
        Date date = new Date(System.currentTimeMillis());
        Integer dayTemp = 25;
        Integer nightTemp = 15;
        Float waterAmount = 2.5f;
        Float nutrientAmount = 1.2f;
        Integer height = 180;
        Integer width = 100;
        Float yield = 12.5f;
        Integer growthStage = 3;
        boolean pestIssues = false;
        boolean diseaseIssues = true;
        String comment = "no comments";
        Byte[] image = new Byte[0];

        // Set the data
        entry.setCalendar(calendar);
        entry.setEntryId(entryId);
        entry.setDate(date);
        entry.setDayTemp(dayTemp);
        entry.setNightTemp(nightTemp);
        entry.setWaterAmount(waterAmount);
        entry.setNutrientAmount(nutrientAmount);
        entry.setHeight(height);
        entry.setWidth(width);
        entry.setYield(yield);
        entry.setGrowthStage(growthStage);
        entry.setPestIssues(pestIssues);
        entry.setDiseaseIssues(diseaseIssues);
        entry.setComment(comment);
        entry.setImage(image);

        // Check the data
        assertEquals(calendar, entry.getCalendar());
        assertEquals(entryId, entry.getEntryId());
        assertEquals(date, entry.getDate());
        assertEquals(dayTemp, entry.getDayTemp());
        assertEquals(nightTemp, entry.getNightTemp());
        assertEquals(waterAmount, entry.getWaterAmount());
        assertEquals(nutrientAmount, entry.getNutrientAmount());
        assertEquals(height, entry.getHeight());
        assertEquals(width, entry.getWidth());
        assertEquals(yield, entry.getYield());
        assertEquals(growthStage, entry.getGrowthStage());
        assertEquals(pestIssues, entry.getPestIssues());
        assertEquals(diseaseIssues, entry.getDiseaseIssues());
        assertEquals(comment, entry.getComment());
        assertEquals(image, entry.getImage());

        // Test equals and hashCode
        CalendarEntry entry2 = new CalendarEntry();
        entry2.setCalendar(calendar);
        entry2.setEntryId(entryId);

        assertTrue(entry.equals(entry2) && entry2.equals(entry));
        assertTrue(entry.hashCode() == entry2.hashCode());
    }
}
