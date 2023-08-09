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
        if (!request.containsKey("name") || !request.containsKey("description") || !request.containsKey("plantId")) {
            logger.error("Missing name, description or plantId in request");
            return new ResponseEntity<>("Missing name, description or plantId in request", HttpStatus.BAD_REQUEST);
        }

        // Type check and cast for variety data
        Object varietyObj = request.get("variety");
        if (!(varietyObj instanceof Map)) {
            logger.error("Invalid variety data format");
            return new ResponseEntity<>("Invalid variety data format", HttpStatus.BAD_REQUEST);
        }
        Map<String, String> varietyData = (Map<String, String>) varietyObj;

        // Check for required variety data fields
        if (!varietyData.containsKey("varietyName") || !varietyData.containsKey("varietyDescription")) {
            logger.error("Missing variety name or description in request");
            return new ResponseEntity<>("Missing variety name or description in request", HttpStatus.BAD_REQUEST);
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

        Variety variety = new Variety();
        variety.setVarietyName(varietyData.get("varietyName"));
        variety.setVarietyDescription(varietyData.get("varietyDescription"));
        variety.setPlant(plantOptional.get());

        Variety newVariety = varietyService.save(variety);

        return new ResponseEntity<>(newVariety, HttpStatus.CREATED);
    }


}
