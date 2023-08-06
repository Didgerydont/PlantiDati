package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Variety;

import java.util.List;
import java.util.Optional;


public interface VarietyService {

    Optional<Variety> findById(int id);

    List<Variety> findByPlantId(int plantId);

    Variety save(Variety variety);

    void updateVarietyTable();


}