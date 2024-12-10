package com.spring.appdemo.dto;

public class LoginRequestDTO {

    private String username;
    private String password;

    // Default constructor (optional, as it is automatically provided)
    public LoginRequestDTO() {}

    // Constructor with parameters (optional, but can be helpful)
    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
