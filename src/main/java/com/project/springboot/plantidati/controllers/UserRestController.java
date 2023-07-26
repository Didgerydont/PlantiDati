package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // Method to handle HTTP POST requests for user registration.
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<User> registerNewUser(@Valid @RequestBody User newUser) {
        // Calls the service method to register a new user and stores the returned RegisteredUser object.
        User savedUser = userService.registerNewUser(newUser.getUsername(), newUser.getEmail(), newUser.getPassword(), newUser.getLocation(), newUser.getProfileCaption());
        // Returns the saved user in the response entity with an HTTP status of CREATED (201).
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Check if username is already taken, use JS to advise the client in the form if this is the case.
    @GetMapping("/isusernametaken")
    public ResponseEntity<Boolean> isUsernameTaken(@RequestParam String username) {
        Optional<User> existingUser = userService.findUserByUsername(username);
        return new ResponseEntity<>(existingUser.isPresent(), HttpStatus.OK);
    }


    // Method to handle HTTP PUT requests for updating a user's email.
    @PutMapping("/{userId}/updateEmail")
    public ResponseEntity<User> updateUserEmail(@PathVariable("userId") int userId, @RequestBody String newEmail) {
        // Calls the service method to find the user by id and stores the returned RegisteredUser object.
        User existingUser = userService.findUserById(userId);
        // Checks if the user exists.
        if (existingUser != null) {
            // If the user exists, calls the service method to update the email and stores the returned RegisteredUser object.
            User updatedUser = userService.setEmail(existingUser, newEmail);
            // Returns the updated user in the response entity with an HTTP status of OK (200).
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            // If the user does not exist, returns an HTTP status of NOT FOUND (404).
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to handle HTTP PUT requests for updating a user's password.
    @PutMapping("/{userId}/updatePassword")
    public ResponseEntity<User> updatePassword(@PathVariable("userId") int userId, @RequestBody String newPassword) {
        User existingUser = userService.findUserById(userId);
        if (existingUser != null) {
            User updatedUser = userService.setPassword(existingUser, newPassword);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to handle HTTP PUT requests for updating a user's location.
    @PutMapping("/{userId}/updateLocation")
    public ResponseEntity<User> updateLocation(@PathVariable("userId") int userId, @RequestBody String newLocation) {
        User existingUser = userService.findUserById(userId);
        if (existingUser != null) {
            User updatedUser = userService.setLocation(existingUser, newLocation);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to handle HTTP PUT requests for updating a user's profile caption.
    @PutMapping("/{userId}/updateProfileCaption")
    public ResponseEntity<User> updateProfileCaption(@PathVariable("userId") int userId, @RequestBody String newProfileCaption) {
        User existingUser = userService.findUserById(userId);
        if (existingUser != null) {
            User updatedUser = userService.setProfileCaption(existingUser, newProfileCaption);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to handle HTTP PUT requests for updating a user's profile picture.
    @PutMapping("/{userId}/updateProfilePic")
    public ResponseEntity<User> updateProfilePic(@PathVariable("userId") int userId, @RequestBody byte[] newProfilePic) {
        User existingUser = userService.findUserById(userId);
        if (existingUser != null) {
            User updatedUser = userService.setProfilePic(existingUser, newProfilePic);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
