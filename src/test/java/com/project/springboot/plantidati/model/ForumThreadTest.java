package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForumThreadTest {

    private ForumThread forumThread;
    private RegisteredUser user;

    @BeforeEach
    public void setUp() {
        forumThread = new ForumThread();
        user = new RegisteredUser();
    }

    @Test
    public void testGetThreadId() {
        forumThread.setThreadId(1);
        assertEquals("Thread1", forumThread.getThreadId());
    }

    @Test
    public void testGetUser() {
        forumThread.setUser(user);
        assertEquals(user, forumThread.getUser());
    }

    @Test
    public void testGetTitle() {
        forumThread.setTitle("Test title");
        assertEquals("Test title", forumThread.getTitle());
    }

    @Test
    public void testGetContent() {
        forumThread.setContent("Test content");
        assertEquals("Test content", forumThread.getContent());
    }

    @Test
    public void testGetDatePosted() {
        LocalDateTime time = LocalDateTime.now();
        forumThread.setDatePosted(time);
        assertEquals(time, forumThread.getDatePosted());
    }
}
