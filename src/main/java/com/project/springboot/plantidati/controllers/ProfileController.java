package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import com.project.springboot.plantidati.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Autowired
    public ProfileController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/uploadProfilePic")
    // ResponseEntity<?> allows for dynamic body
    public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        logger.info("Uploading profile picture...");
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: File is empty.");
        }

        // Extract the JWT from the HttpOnly cookie
        logger.info("Check for cookie...");
        Cookie cookie = WebUtils.getCookie(request, "token");
        if (cookie == null) {
            logger.error("No cookies found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: No cookies found.");
        }

        logger.info("Cookie Found");
        String token = cookie.getValue();

        logger.info("Validating JWT...");
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtService.isTokenValid(token, userDetails)) {
            logger.error("Invalid token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Invalid token.");
        }
        logger.info("JWT validated");
        int userId = jwtService.extractUserId(token);
        Optional<User> userOptional = userRepository.findById(userId);
        logger.info("Find userId");

        if (userOptional.isEmpty()) {
            logger.error("User with ID {} not found", userId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: User not found.");
        }

        User user = userOptional.get();

        // File type validation
        String fileType = file.getContentType();
        if (!"image/png".equals(fileType)
                && !"image/jpeg".equals(fileType)
                && !"image/jpg".equals(fileType)
                && !"image/gif".equals(fileType)
                && !"image/bmp".equals(fileType)
                && !"image/tiff".equals(fileType)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Invalid file type. Please upload a PNG, JPEG, JPG, GIF, BMP, or TIFF image.");
        }

        try {
            byte[] imageBytes = file.getBytes();
            user.setProfilePic(imageBytes);
            userRepository.save(user);

            // Convert byte array to Base64
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Return the Base64 image string
            logger.info("Successfully uploaded profile picture for user: {}", username);
            return ResponseEntity.ok(base64Image);
        } catch (IOException e) {
            logger.error("Error uploading profile picture: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @PutMapping("/{userId}/updatePassword")
    public ResponseEntity<String> updatePassword(@PathVariable("userId") int userId, @RequestBody Map<String, String> body) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existingUserOpt = userRepository.findById(userId);
        String newPassword = body.get("newPassword");

        if (existingUserOpt.isEmpty()) {
            return new ResponseEntity<>("Error: User not found.", HttpStatus.NOT_FOUND);
        }
        // Make sure the client is updating their own profile
        User existingUser = existingUserOpt.get();

        if (!existingUser.getUsername().equals(currentUser.getUsername())) {
            return new ResponseEntity<>("Error: Unauthorized action.", HttpStatus.UNAUTHORIZED);
        }

        existingUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(existingUser);
        return new ResponseEntity<>("Password updated successfully.", HttpStatus.OK);
    }

    @PutMapping("/{userId}/updateLocation")
    public ResponseEntity<String> updateLocation(@PathVariable("userId") int userId, @RequestBody Map<String, String> body) {

        logger.info("Received update location request for user ID: {}", userId);

        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existingUserOpt = userRepository.findById(userId);
        String newLocation = body.get("newLocation");
        if (newLocation == null || newLocation.trim().isEmpty()) {
            logger.warn("Error: Location cannot be blank.");
            return new ResponseEntity<>("Error: Location cannot be blank.", HttpStatus.BAD_REQUEST);
        }


        if (existingUserOpt.isEmpty()) {
            logger.warn("User not found for ID: {}", userId);
            return new ResponseEntity<>("Error: User not found.", HttpStatus.NOT_FOUND);
        }

        User existingUser = existingUserOpt.get();

        if (!existingUser.getUsername().equals(currentUser.getUsername())) {
            logger.warn("Unauthorized action attempt by user: {}", currentUser.getUsername());
            return new ResponseEntity<>("Error: Unauthorized action.", HttpStatus.UNAUTHORIZED);
        }

        logger.info("Updating location for user: {}", existingUser.getUsername());

        existingUser.setLocation(newLocation);
        userRepository.save(existingUser);
        logger.info("Location updated successfully for user: {}", existingUser.getUsername());

        return new ResponseEntity<>("Location updated successfully.", HttpStatus.OK);
    }


    @PutMapping("/{userId}/updateProfileCaption")
    public ResponseEntity<String> updateProfileCaption(@PathVariable("userId") int userId, @RequestBody Map<String, String> body) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Principal: " + principal);
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existingUserOpt = userRepository.findById(userId);
        String newProfileCaption = body.get("newProfileCaption");

        if (existingUserOpt.isEmpty()) {
            return new ResponseEntity<>("Error: User not found.", HttpStatus.NOT_FOUND);
        }

        User existingUser = existingUserOpt.get();

        if (!existingUser.getUsername().equals(currentUser.getUsername())) {
            return new ResponseEntity<>("Error: Unauthorized action.", HttpStatus.UNAUTHORIZED);
        }

        existingUser.setProfileCaption(newProfileCaption);
        userRepository.save(existingUser);
        return new ResponseEntity<>("Profile caption updated successfully.", HttpStatus.OK);
    }
}
