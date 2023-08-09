package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.service.PlantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlantRestControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantRestController controller;


    @Test
    public void getAllPlants_returnsListOfPlants() {
        // Arrange
        Plant plant1 = new Plant();
        Plant plant2 = new Plant();
        List<Plant> mockPlants = Arrays.asList(plant1, plant2);

        when(plantService.findAll()).thenReturn(mockPlants);

        // Act
        List<Plant> returnedPlants = controller.getAllPlants();

        // Assert
        assertEquals(mockPlants, returnedPlants);
        verify(plantService, times(1)).findAll();
    }

    @Test
    public void getPlantById_validId_returnsPlant() {
        // Arrange
        Plant mockPlant = new Plant();
        int testPlantId = 123;

        when(plantService.findById(testPlantId)).thenReturn(Optional.of(mockPlant));

        // Act
        Optional<Plant> returnedPlant = controller.getPlantById(testPlantId);

        // Assert
        assertTrue(returnedPlant.isPresent());
        assertEquals(mockPlant, returnedPlant.get());
    }

    @Test
    public void getPlantById_invalidId_returnsEmpty() {
        // Arrange
        int invalidPlantId = 9999; // largest real id is 33

        when(plantService.findById(invalidPlantId)).thenReturn(Optional.empty());

        // Act
        Optional<Plant> returnedPlant = controller.getPlantById(invalidPlantId);

        // Assert
        assertFalse(returnedPlant.isPresent());
    }

}
