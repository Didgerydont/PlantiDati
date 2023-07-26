package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ForumThread")
public class ForumThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_Id")
    private int threadId;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date_posted")
    private LocalDateTime datePosted;

    // Getter and setter for threadId
    public int getThreadId() {
        return this.threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    // Getter and setter for user
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
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
