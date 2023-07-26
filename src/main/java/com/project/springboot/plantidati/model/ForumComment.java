package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ForumComment")
public class ForumComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_Id")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "thread_Id", nullable = false)
    private ForumThread thread;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_posted")
    private LocalDateTime datePosted;

    // Getter and setter for commentId
    public int getCommentId() {
        return this.commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    // Getter and setter for thread
    public ForumThread getThread() {
        return this.thread;
    }

    public void setThread(ForumThread thread) {
        this.thread = thread;
    }

    // Getter and setter for user
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter and setter for comment
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // Getter and setter for datePosted
    public LocalDateTime getDatePosted() {
        return this.datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

}
