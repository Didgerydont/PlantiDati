package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.Plant;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<Plant, Integer> {
}
