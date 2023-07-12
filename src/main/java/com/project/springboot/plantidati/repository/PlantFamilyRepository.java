package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.PlantFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantFamilyRepository extends JpaRepository<PlantFamily, String> {

}