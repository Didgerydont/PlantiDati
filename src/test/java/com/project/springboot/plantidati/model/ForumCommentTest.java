package com.project.springboot.plantidati.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForumCommentTest {

    private ForumComment forumComment;
    private RegisteredUser user;
    private ForumThread thread;

    @BeforeEach
    public void setUp() {
        forumComment = new ForumComment();
        user = new RegisteredUser();
        thread = new ForumThread();
    }

    @Test
    public void testGetCommentId() {
        forumComment.setCommentId(1);
        assertEquals("Comment1", forumComment.getCommentId());
    }

    @Test
    public void testGetThread() {
        forumComment.setThread(thread);
        assertEquals(thread, forumComment.getThread());
    }

    @Test
    public void testGetUser() {
        forumComment.setUser(user);
        assertEquals(user, forumComment.getUser());
    }

    @Test
    public void testGetComment() {
        forumComment.setComment("Test comment");
        assertEquals("Test comment", forumComment.getComment());
    }

    @Test
    public void testGetDatePosted() {
        LocalDateTime time = LocalDateTime.now();
        forumComment.setDatePosted(time);
        assertEquals(time, forumComment.getDatePosted());
    }
}