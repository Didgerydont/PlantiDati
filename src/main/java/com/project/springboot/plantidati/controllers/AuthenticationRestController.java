package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import com.project.springboot.plantidati.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {
    private final AuthenticationService authService;
    private final UserRepository userRepository;

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
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AutenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/isusernametaken")
    public ResponseEntity<Boolean> isUsernameTaken(@RequestParam String username) {
        Optional<User> existingUser = authService.findUserByUsername(username);
        return new ResponseEntity<>(existingUser.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/{userId}/updateEmail")
    public ResponseEntity<User> updateUserEmail(@PathVariable("userId") int userId, @RequestBody String newEmail) {
        Optional<User> existingUserOpt = authService.findUserById(userId);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setEmail(newEmail);
            User updatedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/updatePassword")
    public ResponseEntity<User> updatePassword(@PathVariable("userId") int userId, @RequestBody String newPassword) {
        Optional<User> existingUserOpt = authService.findUserById(userId);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setPassword(newPassword);
            User updatedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/updateLocation")
    public ResponseEntity<User> updateLocation(@PathVariable("userId") int userId, @RequestBody String newLocation) {
        Optional<User> existingUserOpt = authService.findUserById(userId);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setLocation(newLocation);
            User updatedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/updateProfileCaption")
    public ResponseEntity<User> updateProfileCaption(@PathVariable("userId") int userId, @RequestBody String newProfileCaption) {
        Optional<User> existingUserOpt = authService.findUserById(userId);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setProfileCaption(newProfileCaption);
            User updatedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/updateProfilePic")
    public ResponseEntity<User> updateProfilePic(@PathVariable("userId") int userId, @RequestBody byte[] newProfilePic) {
        Optional<User> existingUserOpt = authService.findUserById(userId);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setProfilePic(newProfilePic);
            User updatedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
