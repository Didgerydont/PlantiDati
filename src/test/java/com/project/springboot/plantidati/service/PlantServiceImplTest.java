package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Plant;
import com.project.springboot.plantidati.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlantServiceImplTest {

    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private PlantServiceImpl plantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Plant plant = new Plant();
        plant.setPlantId(1);
        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));

        Optional<Plant> result = plantService.findById(1);

        assertEquals(plant, result.orElse(null));
        verify(plantRepository).findById(1);
    }

    @Test
    void findAll() {
        Plant plant1 = new Plant();
        Plant plant2 = new Plant();
        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));

        List<Plant> result = plantService.findAll();

        assertEquals(2, result.size());
        verify(plantRepository).findAll();
    }
}


