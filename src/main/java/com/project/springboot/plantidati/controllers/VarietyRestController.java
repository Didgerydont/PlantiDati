package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.controllers.dto.CreateVarietyRequestDTO;
import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.service.PlantService;
import com.project.springboot.plantidati.service.VarietyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/variety")
public class VarietyRestController {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(VarietyRestController.class);
    @Autowired
    private VarietyService varietyService;
    @Autowired
    private PlantService plantService;

    @GetMapping("/getVarietiesByPlantId/{plantId}")
    public ResponseEntity<List<Variety>> getVarietiesByPlantId(@PathVariable("plantId") int plantId) {
        List<Variety> varieties = varietyService.findByPlantId(plantId);
        return ResponseEntity.ok(varieties);
    }


    @PostMapping("/createVariety")
    public ResponseEntity<?> createVariety(@RequestBody CreateVarietyRequestDTO request) {

        Optional<Plant> plantOptional = plantService.findById(request.getPlantId());
        if (plantOptional.isEmpty()) {
            logger.error("No Plant found with given ID");
            return new ResponseEntity<>("No Plant found with given ID", HttpStatus.BAD_REQUEST);
        }

        Variety variety = new Variety();
        variety.setVarietyName(request.getName());
        variety.setVarietyDescription(request.getDescription());
        variety.setPlant(plantOptional.get());

        Variety newVariety = varietyService.save(variety);

        return new ResponseEntity<>(newVariety, HttpStatus.CREATED);
    }


}
