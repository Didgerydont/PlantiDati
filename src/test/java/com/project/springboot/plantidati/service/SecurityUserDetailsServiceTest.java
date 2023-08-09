package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SecurityUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SecurityUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void loadUserByUsername_WithExistingUsername() {
        // Create a mock user for testing
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        // Define behavior for the mock UserRepository
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Call the method under test
        UserDetails result = userDetailsService.loadUserByUsername("testUser");

        // Assert the expected results
        assertEquals("testUser", result.getUsername());
        assertEquals("testPassword", result.getPassword());
        assertTrue(result.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));

        // Verify that the mock method was called as expected
        verify(userRepository).findByUsername("testUser");
    }

    // Test case for when the username is not found in the repository
    @Test
    void loadUserByUsername_WithNonExistingUsername() {
        // Define behavior for the mock UserRepository to return empty
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(Optional.empty());

        // Assert that the expected exception is thrown
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonExistentUser");
        });

        // Verify that the mock method was called as expected
        verify(userRepository).findByUsername("nonExistentUser");
    }
}
