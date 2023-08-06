package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VarietyServiceImpl implements VarietyService {

    @Autowired
    private VarietyRepository varietyRepository;

    @Override
    public Optional<Variety> findById(int id) {
        return varietyRepository.findById(id);
    }

    @Override
    public List<Variety> findByPlantId(int plantId) {
        return varietyRepository.findByPlant_PlantId(plantId);
    }

    @Override
    public Variety save(Variety variety) {
        return varietyRepository.save(variety);
    }

    // run every 180 seconds
    @Scheduled(fixedRate = 180000)
    @Override
    public void updateVarietyTable() {
        varietyRepository.updateVarietyAverages();
    }
}
