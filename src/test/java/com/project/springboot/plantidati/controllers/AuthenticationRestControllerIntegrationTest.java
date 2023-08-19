package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.controllers.dto.AuthenticationRequest;
import com.project.springboot.plantidati.controllers.dto.AuthenticationResponse;
import com.project.springboot.plantidati.controllers.dto.RegisterRequest;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import com.project.springboot.plantidati.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationRestControllerIntegrationTest {

    @Mock
    private AuthenticationService authService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationRestController controller;

    @BeforeEach
    public void setup() {
        SecurityContextHolder.clearContext(); // Clear context
    }

    // Register Tests
    @Nested
    class RegisterTests {
        @Test
        public void registerTest_successfullyRegisters() {
            // Arrange
            AuthenticationResponse response = new AuthenticationResponse("testToken");
            when(authService.register(any())).thenReturn(response);

            // Act
            ResponseEntity<AuthenticationResponse> result = controller.register(new RegisterRequest());

            // Assert
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals("testToken", result.getBody().getToken());
        }


    }

    // Authentication Tests
    @Nested
    class AuthenticateTests {
        @Test
        public void authenticateTest_validUser_returnsTokenInCookie() throws Exception {
            // Arrange
            AuthenticationResponse response = new AuthenticationResponse("sampleToken");
            when(authService.authenticate(any())).thenReturn(response);
            MockHttpServletResponse mockResponse = new MockHttpServletResponse();

            // Act
            ResponseEntity<String> result = controller.authenticate(new AuthenticationRequest(), mockResponse);

            // Assert
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertNotNull(mockResponse.getCookie("token"));
        }


    }

    // User Profile Tests
    @Nested
    class UserProfileTests {
        @Test
        public void getProfileTest_userExists_returnsProfile() {
            // Arrange
            Authentication authMock = mock(Authentication.class);
            when(authMock.getName()).thenReturn("testUser");
            SecurityContextHolder.getContext().setAuthentication(authMock);
            when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(new User()));

            // Act
            ResponseEntity<User> result = controller.getProfile();

            // Assert
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertNotNull(result.getBody());
        }

        @Test
        public void getProfileTest_userDoesNotExist_returnsNotFound() {
            // Arrange
            Authentication authMock = mock(Authentication.class);
            when(authMock.getName()).thenReturn("testUser");
            SecurityContextHolder.getContext().setAuthentication(authMock);
            when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());

            // Act
            ResponseEntity<User> result = controller.getProfile();

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        }


    }

    // Check Username Tests
    @Nested
    class UsernameCheckTests {
        @Test
        public void isUsernameTaken_usernameExists_returnsTrue() {
            // Arrange
            when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(new User()));

            // Act
            ResponseEntity<Boolean> result = controller.isUsernameTaken("testUser");

            // Assert
            assertTrue(result.getBody());
            assertEquals(HttpStatus.OK, result.getStatusCode());
        }

        @Test
        public void isUsernameTaken_usernameDoesNotExist_returnsFalse() {
            // Arrange
            when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());

            // Act
            ResponseEntity<Boolean> result = controller.isUsernameTaken("testUser");

            // Assert
            assertNotEquals(Boolean.TRUE, result.getBody());
            assertEquals(HttpStatus.OK, result.getStatusCode());
        }


    }
}
