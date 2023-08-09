package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.controllers.dto.AuthenticationRequest;
import com.project.springboot.plantidati.controllers.dto.AuthenticationResponse;
import com.project.springboot.plantidati.controllers.dto.RegisterRequest;
import com.project.springboot.plantidati.model.Role;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(RegisterRequest request) {
        try {
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .location(request.getLocation())
                    .profileCaption(request.getProfileCaption())
                    .role(Role.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            logger.error("Error during registration", e);
            throw e;
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Log the request email
        logger.info("Authentication attempt for username: " + request.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        logger.info("Authentication: " + authentication.isAuthenticated());
        var user = repository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            logger.info("User found");
            var jwtToken = jwtService.generateToken(user.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            logger.info("User not found");
            throw new UsernameNotFoundException("User not found");
        }
    }

}
