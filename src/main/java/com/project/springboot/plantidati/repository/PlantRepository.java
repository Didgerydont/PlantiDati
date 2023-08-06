package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Integer> {

}
