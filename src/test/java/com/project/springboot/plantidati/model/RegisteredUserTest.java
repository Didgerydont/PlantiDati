package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisteredUserTest {
    private User user;
    private final byte[] profilePic = "Test".getBytes();

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("TestUsername");
        user.setEmail("test@example.com");
        user.setPassword("TestPassword");
        user.setLocation("TestLocation");
        user.setProfileCaption("TestProfileCaption");
        user.setProfilePic(profilePic);
    }

    @Test
    void getUserId() {
        assertEquals(0, user.getUserId());
    }

    @Test
    void getUsername() {
        assertEquals("TestUsername", user.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    void getLocation() {
        assertEquals("TestLocation", user.getLocation());
    }

    @Test
    void getProfileCaption() {
        assertEquals("TestProfileCaption", user.getProfileCaption());
    }

    @Test
    void getProfilePic() {
        assertArrayEquals(profilePic, user.getProfilePic());
    }

}