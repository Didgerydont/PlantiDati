package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import com.project.springboot.plantidati.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {
    private final AuthenticationService authService;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);


    @Autowired
    public AuthenticationRestController(AuthenticationService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        try {
            AuthenticationResponse authResponse = authService.authenticate(request);
            String jwt = authResponse.getToken();

            // Create a new cookie
            Cookie cookie = new Cookie("token", jwt);
            cookie.setHttpOnly(true);
            //cookie.setSecure(true);
            cookie.setPath("/"); // available to entire domain

            // Add the cookie to the response
            response.addCookie(cookie);
            return ResponseEntity.ok("{\"message\": \"User logged in\"}");
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password", e);
        }
    }

    @GetMapping("/getProfile")
    public ResponseEntity<User> getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<User> existingUser = authService.findUserByUsername(username);
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(existingUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/isusernametaken")
    public ResponseEntity<Boolean> isUsernameTaken(@RequestParam String username) {
        Optional<User> existingUser = authService.findUserByUsername(username);
        return new ResponseEntity<>(existingUser.isPresent(), HttpStatus.OK);
    }
    
}


