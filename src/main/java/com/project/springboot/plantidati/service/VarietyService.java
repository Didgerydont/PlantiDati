package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class VarietyService {

    @Autowired
    VarietyRepository varietyRepository;

    // run every 180 seconds
    @Scheduled(fixedRate = 180000)
    public void updateVarietyTable() {
        // call to repository method that executes the SQL update
        varietyRepository.updateVarietyAverages();
    }
}