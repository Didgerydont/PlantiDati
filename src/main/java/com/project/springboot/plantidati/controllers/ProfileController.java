package com.project.springboot.plantidati.controllers;

import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import com.project.springboot.plantidati.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    public ProfileController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/uploadProfilePic")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if (file.isEmpty()) {
            return "Error: File is empty.";
        }

        // Extract the JWT from the HttpOnly cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();

                    String username = jwtService.extractUsername(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtService.isTokenValid(token, userDetails)) {
                        int userId = jwtService.extractUserId(token);

                        Optional<User> userOptional = userRepository.findById(userId);

                        if (userOptional.isPresent()) {
                            User user = userOptional.get();
                            try {
                                user.setProfilePic(file.getBytes());
                                userRepository.save(user);
                                return "File uploaded successfully!";
                            } catch (IOException e) {
                                return "Error: " + e.getMessage();
                            }
                        } else {
                            return "Error: User not found.";
                        }
                    }
                }
            }
        }

        return "Error: Unauthorized action.";
    }


    @PutMapping("/{userId}/updatePassword")
    public ResponseEntity<String> updatePassword(@PathVariable("userId") int userId, @RequestBody Map<String, String> body) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existingUserOpt = userRepository.findById(userId);
        String newPassword = body.get("newPassword");

        if (!existingUserOpt.isPresent()) {
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
    // Using map so that the update will be recieved in JSON
    public ResponseEntity<String> updateLocation(@PathVariable("userId") int userId, @RequestBody Map<String, String> body) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existingUserOpt = userRepository.findById(userId);
        String newLocation = body.get("newLocation");

        if (!existingUserOpt.isPresent()) {
            return new ResponseEntity<>("Error: User not found.", HttpStatus.NOT_FOUND);
        }

        User existingUser = existingUserOpt.get();

        if (!existingUser.getUsername().equals(currentUser.getUsername())) {
            return new ResponseEntity<>("Error: Unauthorized action.", HttpStatus.UNAUTHORIZED);
        }

        existingUser.setLocation(newLocation);
        userRepository.save(existingUser);
        return new ResponseEntity<>("Location updated successfully.", HttpStatus.OK);
    }

    @PutMapping("/{userId}/updateProfileCaption")
    public ResponseEntity<String> updateProfileCaption(@PathVariable("userId") int userId, @RequestBody Map<String, String> body) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existingUserOpt = userRepository.findById(userId);
        String newProfileCaption = body.get("newProfileCaption");

        if (!existingUserOpt.isPresent()) {
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
