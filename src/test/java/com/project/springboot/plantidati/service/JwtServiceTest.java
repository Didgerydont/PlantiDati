package com.project.springboot.plantidati.service;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JwtServiceTest {
    private JwtService jwtService;
    private String secretKey = "YourSecretKey";
    private User testUser;

    @Before
    public void setUp() {
        jwtService = new JwtService(secretKey);
        testUser = new User("testUsername", "testPassword", new ArrayList<>());
    }

    @Test
    public void generateTokenTest() {
        String token = jwtService.generateToken(testUser);
        assertNotNull(token);
    }

    @Test
    public void isTokenValidTest() {
        String token = jwtService.generateToken(testUser);
        assertTrue(jwtService.isTokenValid(token, testUser));
    }

    @Test
    public void isTokenExpiredTest() {
        String token = jwtService.generateToken(testUser);
        assertFalse(jwtService.isTokenExpired(token));
    }

    @Test
    public void extractUsernameTest() {
        String token = jwtService.generateToken(testUser);
        assertEquals("testUsername", jwtService.extractUsername(token));
    }

    @Test
    public void extractClaimTest() {
        String token = jwtService.generateToken(testUser);
        assertEquals("testUsername", jwtService.extractClaim(token, Claims::getSubject));
    }
}
