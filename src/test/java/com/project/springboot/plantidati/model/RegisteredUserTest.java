package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisteredUserTest {
    private RegisteredUser user;

    @BeforeEach
    public void setUp() {
        user = new RegisteredUser();
    }

    @Test
    public void testUserId() {
        String userId = "testUser";
        user.setUserId(userId);
        assertEquals(userId, user.getUserId());
    }

    @Test
    public void testUsername() {
        String username = "TestUsername";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

}