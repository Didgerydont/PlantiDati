package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private int userId;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password must be created")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Location cannot be blank.")
    @Column(name = "location")
    private String location;

    @Column(name = "profile_caption", columnDefinition = "TEXT")
    private String profileCaption;

    @Lob
    @Column(name = "profile_pic")
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