package com.project.springboot.plantidati.controllers;

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
import java.util.Map;
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

    @GetMapping("/plant/{plantId}")
    public ResponseEntity<List<Variety>> getVarietiesByPlantId(@PathVariable("plantId") int plantId) {
        List<Variety> varieties = varietyService.findByPlantId(plantId);
        return ResponseEntity.ok(varieties);
    }


    @PostMapping("/createVariety")
    public ResponseEntity<?> createVariety(@RequestBody Map<String, Object> request) {

        // Check presence of required keys
        if (!request.containsKey("name")) {
            logger.error("No name in request");
            return new ResponseEntity<>("Missing name, description or plantId in request", HttpStatus.BAD_REQUEST);
        } else if (!request.containsKey("description")) {
            logger.error("No description in request");
            return new ResponseEntity<>("No description in request", HttpStatus.BAD_REQUEST);
        } else if (!request.containsKey("plantId")) {
            logger.error("No plantId in request");
            return new ResponseEntity<>("No plantId in request", HttpStatus.BAD_REQUEST);
        }

        // Type check and cast for plantId
        Object plantIdObj = request.get("plantId");
        if (!(plantIdObj instanceof Integer)) {
            logger.error("Invalid plantId format");
            return new ResponseEntity<>("Invalid plantId format", HttpStatus.BAD_REQUEST);
        }
        int plantId = (Integer) plantIdObj;

        Optional<Plant> plantOptional = plantService.findById(plantId);
        if (plantOptional.isEmpty()) {
            logger.error("No Plant found with given ID");
            return new ResponseEntity<>("No Plant found with given ID", HttpStatus.BAD_REQUEST);
        }

        // Directly get name and description from the request body
        String name = (String) request.get("name");
        String description = (String) request.get("description");

        Variety variety = new Variety();
        variety.setVarietyName(name);
        variety.setVarietyDescription(description);
        variety.setPlant(plantOptional.get());

        Variety newVariety = varietyService.save(variety);

        return new ResponseEntity<>(newVariety, HttpStatus.CREATED);
    }


}
