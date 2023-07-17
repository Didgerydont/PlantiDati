package com.project.springboot.plantidati.service;
import com.project.springboot.plantidati.repository.VarietyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VarietyService {

    @Autowired
    VarietyRepository varietyRepository;

    @Scheduled(fixedRate = 180000) // this will run every 180 seconds
    public void updateVarietyTable() {
        // call to your repository method that executes the SQL update
        varietyRepository.updateVarietyAverages();
    }
}