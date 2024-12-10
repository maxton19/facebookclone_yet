package com.spring.appdemo.payload;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Object getUsername() {
        return username;
    }

    public Object getPassword() {
        return password;
    }

    // Getters and setters
}
