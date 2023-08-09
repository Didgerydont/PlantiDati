package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.service.PlantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plant")
public class PlantRestController {

    private final PlantService plantService;

    public PlantRestController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/getAll")
    public List<Plant> getAllPlants() {
        return plantService.findAll();
    }

//    @GetMapping("/getAll")
//    public String getAllPlants() {
//        return "Test Response";
//    }

    @GetMapping("/varieties/{plantId}")
    public Optional<Plant> getPlantById(@PathVariable int plantId) {
        System.out.println("Accessing plant by ID: " + plantId);
        return plantService.findById(plantId);
    }

}
