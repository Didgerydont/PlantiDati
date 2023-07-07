package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ForumThread")
public class ForumThread {

    @Id
    @Column(name = "threadId")
    private String threadId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private RegisteredUser user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "datePosted")
    private LocalDateTime datePosted;

    // Getter and setter for threadId
    public String getThreadId() {
        return this.threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    // Getter and setter for user
    public RegisteredUser getUser() {
        return this.user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    // Getter and setter for title
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for content
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter and setter for datePosted
    public LocalDateTime getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }
}
