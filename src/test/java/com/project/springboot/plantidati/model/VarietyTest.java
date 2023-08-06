package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


// not testing all due to size of the entity
public class VarietyTest {

    private Variety variety;
    private Plant plant;

    @BeforeEach
    public void setUp() {
        variety = new Variety();
        plant = new Plant();
    }

    @Test
    public void testGetVarietyId() {
        variety.setVarietyId(1);
        assertEquals(1, variety.getVarietyId());
    }

    @Test
    void testVarietyRelationshipWithPlant() {
        Plant plant = new Plant();
        variety.setPlant(plant);
        assertEquals(plant, variety.getPlant());
    }

    @Test
    public void testGetVarietyName() {
        variety.setVarietyName("Variety Name");
        assertEquals("Variety Name", variety.getVarietyName());
    }

    @Test
    public void testGetVarietyDescription() {
        variety.setVarietyDescription("Variety Description");
        assertEquals("Variety Description", variety.getVarietyDescription());
    }

    @Test
    public void testGetVarietyHeight() {
        variety.setVarietyHeight(20);
        assertEquals(20, variety.getVarietyHeight());
    }

    @Test
    public void testGetVarietyWidth() {
        variety.setVarietyWidth(30);
        assertEquals(30, variety.getVarietyWidth());
    }

    @Test
    public void testGetVarietyYield() {
        variety.setVarietyYield(15.5f);
        assertEquals(15.5f, variety.getVarietyYield());
    }

    @Test
    public void testGetGrowthRate() {
        variety.setGrowthRate(5);
        assertEquals(5, variety.getGrowthRate());
    }

    @Test
    public void testGetWateringRequirement() {
        variety.setWateringRequirement(3);
        assertEquals(3, variety.getWateringRequirement());
    }


}


