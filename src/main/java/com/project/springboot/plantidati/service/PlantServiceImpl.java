package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    public PlantServiceImpl(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public Optional<Plant> findById(int plantId) {
        return plantRepository.findById(plantId);
    }

    @Override
    public List<Plant> findAll() {
        return plantRepository.findAll();
    }
}
