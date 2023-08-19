package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.controllers.dto.CreateVarietyRequestDTO;
import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.service.PlantService;
import com.project.springboot.plantidati.service.VarietyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class VarietyRestControllerIntegrationTest {

    @InjectMocks
    private VarietyRestController varietyRestController;

    @Mock
    private VarietyService varietyService;

    @Mock
    private PlantService plantService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getVarietiesByPlantIdTest() {
        Variety variety1 = new Variety();
        variety1.setVarietyName("Variety A");
        Variety variety2 = new Variety();
        variety2.setVarietyName("Variety B");

        when(varietyService.findByPlantId(1)).thenReturn(Arrays.asList(variety1, variety2));

        ResponseEntity<List<Variety>> response = varietyRestController.getVarietiesByPlantId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void createVarietyTest() {
        Plant plant = new Plant();
        plant.setPlantId(1);

        Variety variety = new Variety();
        variety.setVarietyName("Variety A");
        variety.setVarietyDescription("Description A");

        when(plantService.findById(anyInt())).thenReturn(Optional.of(plant));
        when(varietyService.save(any(Variety.class))).thenReturn(variety);

        CreateVarietyRequestDTO request = new CreateVarietyRequestDTO();
        request.setName("Test Name");
        request.setDescription("Test Description");
        request.setPlantId(1);

        ResponseEntity<?> response = varietyRestController.createVariety(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    public void createVarietyMissingDataTest() {
        CreateVarietyRequestDTO request = new CreateVarietyRequestDTO();
        ResponseEntity<?> response = varietyRestController.createVariety(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
