package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VarietyRepository extends JpaRepository<Variety, String> {

}