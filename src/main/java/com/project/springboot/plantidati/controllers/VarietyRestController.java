package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.service.PlantService;
import com.project.springboot.plantidati.service.VarietyService;
import lombok.AllArgsConstructor;
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
        if (!request.containsKey("name") || !request.containsKey("description") || !request.containsKey("plantId")) {
            return new ResponseEntity<>("Missing name, description or plantId in request", HttpStatus.BAD_REQUEST);
        }

        Map<String, String> varietyData = (Map<String, String>) request.get("variety");
        if (!varietyData.containsKey("varietyName") || !varietyData.containsKey("varietyDescription")) {
            return new ResponseEntity<>("Missing variety name or description in request", HttpStatus.BAD_REQUEST);
        }

        Variety variety = new Variety();

        variety.setVarietyName(varietyData.get("varietyName"));
        variety.setVarietyDescription(varietyData.get("varietyDescription"));

        int plantId = (Integer) request.get("plantId");
        Optional<Plant> plantOptional = plantService.findById(plantId);  // using the PlantService
        if (!plantOptional.isPresent()) {
            return new ResponseEntity<>("No Plant found with given ID", HttpStatus.BAD_REQUEST);
        }
        variety.setPlant(plantOptional.get());

        Variety newVariety = varietyService.save(variety);

        return new ResponseEntity<>(newVariety, HttpStatus.CREATED);
    }


}
