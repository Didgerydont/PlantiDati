package com.project.springboot.plantidati.model;

import jakarta.persistence.*;


@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String location;

    @Column(columnDefinition = "TEXT")
    private String profileCaption;

    @Lob
    private byte[] profilePic;

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getProfileCaption() {
        return profileCaption;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfileCaption(String profileCaption) {
        this.profileCaption = profileCaption;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
}