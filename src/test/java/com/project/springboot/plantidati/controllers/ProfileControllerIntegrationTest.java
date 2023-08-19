package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import com.project.springboot.plantidati.service.JwtService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfileControllerIntegrationTest {

    // Mock instances
    @Mock
    private JwtService jwtService;


    @Mock
    private UserDetailsService userDetailsService;


    @Mock
    private UserRepository userRepository;


    @Mock
    private PasswordEncoder passwordEncoder;

    // Inject the mock objects into the ProfileController
    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSingleFileUpload() {
        MockMultipartFile file = new MockMultipartFile("file", "filename.png", "image/png", "some-image".getBytes());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(new Cookie("token", "testToken"));

        when(jwtService.extractUsername(anyString())).thenReturn("username");
        when(userDetailsService.loadUserByUsername("username")).thenReturn(mock(UserDetails.class));
        when(jwtService.extractUserId(anyString())).thenReturn(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(mock(User.class)));

        // Ensure the JWT token is always valid for test case
        when(jwtService.isTokenValid(anyString(), any(UserDetails.class))).thenReturn(true);

        ResponseEntity<?> response = profileController.singleFileUpload(file, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}


