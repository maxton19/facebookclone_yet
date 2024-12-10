package com.spring.appdemo.dto;

public class UserProfileDTO {
    private Long userId;
    private String username;
    private String bio;
    private String profilePic;

    // Default constructor
    public UserProfileDTO() {
    }

    // Constructor with parameters
    public UserProfileDTO(Long userId, String username, String bio, String profilePic) {
        this.userId = userId;
        this.username = username;
        this.bio = bio;
        this.profilePic = profilePic;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
