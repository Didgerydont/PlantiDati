package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Plant;

import java.util.List;
import java.util.Optional;

public interface PlantService {
    Optional<Plant> findById(int id);

    List<Plant> findAll();
}
