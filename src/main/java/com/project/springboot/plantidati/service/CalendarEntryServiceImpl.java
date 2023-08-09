package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.exception.CalendarEntryNotFoundException;
import com.project.springboot.plantidati.exception.CalendarNotFoundException;
import com.project.springboot.plantidati.model.Calendar;
import com.project.springboot.plantidati.model.CalendarEntry;
import com.project.springboot.plantidati.model.dto.CalendarEntryDTO;
import com.project.springboot.plantidati.repository.CalendarEntryRepository;
import com.project.springboot.plantidati.repository.CalendarRepository;
import com.project.springboot.plantidati.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CalendarEntryServiceImpl implements CalendarEntryService {
    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private CalendarEntryRepository calendarEntryRepository;

    @Autowired
    private VarietyRepository varietyRepository;

    @Override
    public CalendarEntryDTO createCalendarEntryForCalendar(int calendarId, CalendarEntryDTO calendarEntryDTO) {
        // Fetch the specific Calendar using the provided calendarId
        Calendar userCalendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CalendarNotFoundException("Calendar not found"));

        CalendarEntry calendarEntry = convertDtoToEntity(calendarEntryDTO);
        calendarEntry.setCalendar(userCalendar);

        CalendarEntry savedEntry = calendarEntryRepository.save(calendarEntry);

        return convertEntityToDto(savedEntry);
    }

    private CalendarEntry convertDtoToEntity(CalendarEntryDTO calendarEntryDTO) {
        CalendarEntry calendarEntry = new CalendarEntry();

        calendarEntry.setDate(calendarEntryDTO.getDate());
        calendarEntry.setDayTemp(calendarEntryDTO.getDayTemp());
        calendarEntry.setNightTemp(calendarEntryDTO.getNightTemp());
        calendarEntry.setWaterAmount(calendarEntryDTO.getWaterAmount());
        calendarEntry.setNutrientAmount(calendarEntryDTO.getNutrientAmount());
        calendarEntry.setHeight(calendarEntryDTO.getHeight());
        calendarEntry.setWidth(calendarEntryDTO.getWidth());
        calendarEntry.setGrowthStage(calendarEntryDTO.getGrowthStage());
        calendarEntry.setPestIssues(calendarEntryDTO.isPestIssues());
        calendarEntry.setPestImpact(calendarEntryDTO.getPestImpact());
        calendarEntry.setDiseaseIssues(calendarEntryDTO.isDiseaseIssues());
        calendarEntry.setDiseaseImpact(calendarEntryDTO.getDiseaseImpact());
        calendarEntry.setComment(calendarEntryDTO.getComment());
        calendarEntry.setLightAmount(calendarEntryDTO.getLightAmount());
        calendarEntry.setHarvested(calendarEntryDTO.isHarvested());
        calendarEntry.setYield(calendarEntryDTO.getYield());
        calendarEntry.setImage(calendarEntryDTO.getImage());


        return calendarEntry;
    }

    private CalendarEntryDTO convertEntityToDto(CalendarEntry calendarEntry) {
        CalendarEntryDTO dto = new CalendarEntryDTO();

        dto.setEntryId(calendarEntry.getEntryId());
        dto.setDate(calendarEntry.getDate());
        dto.setDayTemp(calendarEntry.getDayTemp());
        dto.setNightTemp(calendarEntry.getNightTemp());
        dto.setWaterAmount(calendarEntry.getWaterAmount());
        dto.setNutrientAmount(calendarEntry.getNutrientAmount());
        dto.setHeight(calendarEntry.getHeight());
        dto.setWidth(calendarEntry.getWidth());
        dto.setGrowthStage(calendarEntry.getGrowthStage());
        dto.setPestIssues(calendarEntry.isPestIssues());
        dto.setPestImpact(calendarEntry.getPestImpact());
        dto.setDiseaseIssues(calendarEntry.isDiseaseIssues());
        dto.setDiseaseImpact(calendarEntry.getDiseaseImpact());
        dto.setComment(calendarEntry.getComment());
        dto.setLightAmount(calendarEntry.getLightAmount());
        dto.setHarvested(calendarEntry.isHarvested());
        dto.setYield(calendarEntry.getYield());
        dto.setImage(calendarEntry.getImage());

        return dto;

    }


    @Override
    public List<CalendarEntryDTO> returnFullCalendar(int calendarId) {
        List<CalendarEntry> entries = calendarEntryRepository.findByCalendar_CalendarId(calendarId);
        return entries.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public CalendarEntryDTO updateCalendarEntryForCalendar(int calendarId, int entryId, CalendarEntryDTO calendarEntryDTO) {
        // Fetch the specific Calendar using the provided calendarId
        Calendar userCalendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CalendarNotFoundException("Calendar not found"));

        // Fetch the specific CalendarEntry using the provided entryId
        CalendarEntry existingEntry = calendarEntryRepository.findById(entryId)
                .orElseThrow(() -> new CalendarEntryNotFoundException("CalendarEntry not found"));


        // Ensure that the existingEntry is associated with the provided calendarId
        if (existingEntry.getCalendar().getCalendarId() != userCalendar.getCalendarId()) {
            throw new RuntimeException("CalendarEntry does not belong to the specified Calendar");
        }

        CalendarEntry updatedDetails = convertDtoToEntity(calendarEntryDTO);

        existingEntry.setDate(updatedDetails.getDate());
        existingEntry.setDayTemp(updatedDetails.getDayTemp());
        existingEntry.setNightTemp(updatedDetails.getNightTemp());
        existingEntry.setWaterAmount(updatedDetails.getWaterAmount());
        existingEntry.setNutrientAmount(updatedDetails.getNutrientAmount());
        existingEntry.setHeight(updatedDetails.getHeight());
        existingEntry.setWidth(updatedDetails.getWidth());
        existingEntry.setGrowthStage(updatedDetails.getGrowthStage());
        existingEntry.setPestIssues(updatedDetails.isPestIssues());
        existingEntry.setPestImpact(updatedDetails.getPestImpact());
        existingEntry.setDiseaseIssues(updatedDetails.isDiseaseIssues());
        existingEntry.setDiseaseImpact(updatedDetails.getDiseaseImpact());
        existingEntry.setComment(updatedDetails.getComment());
        existingEntry.setLightAmount(updatedDetails.getLightAmount());
        existingEntry.setHarvested(updatedDetails.isHarvested());
        existingEntry.setYield(updatedDetails.getYield());
        existingEntry.setImage(updatedDetails.getImage());


        // Save the updateexistingEntry
        CalendarEntry savedEntry = calendarEntryRepository.save(existingEntry);

        // Convert to DTO and return
        return convertEntityToDto(savedEntry);
    }


}
