package com.spring.appdemo.dto;

public class LoginResponseDTO {

    private String token;

    // Constructor
    public LoginResponseDTO(String token) {
        this.token = token;
    }

    // Getter for token
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }
}
