package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.Variety;
import com.project.springboot.plantidati.repository.VarietyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VarietyServiceImplTest {

    @Mock
    private VarietyRepository varietyRepository;

    @InjectMocks
    private VarietyServiceImpl varietyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Variety variety = new Variety();
        variety.setVarietyId(1);
        when(varietyRepository.findById(1)).thenReturn(Optional.of(variety));

        Optional<Variety> foundVariety = varietyService.findById(1);

        assertTrue(foundVariety.isPresent());
        assertEquals(1, foundVariety.get().getVarietyId());
    }

    @Test
    void findByPlantId() {
        Variety variety1 = new Variety();
        Variety variety2 = new Variety();
        when(varietyRepository.findByPlant_PlantId(1)).thenReturn(Arrays.asList(variety1, variety2));

        List<Variety> varieties = varietyService.findByPlantId(1);

        assertNotNull(varieties);
        assertEquals(2, varieties.size());
    }

    @Test
    void save() {
        Variety variety = new Variety();
        when(varietyRepository.save(any(Variety.class))).thenReturn(variety);

        Variety savedVariety = varietyService.save(variety);

        assertNotNull(savedVariety);
    }

    @Test
    void updateVarietyTable() {
        // Just verifying if the method is called. Actual logic would be tested in an integration test.
        varietyService.updateVarietyTable();
        verify(varietyRepository).updateVarietyAverages();
    }
}
