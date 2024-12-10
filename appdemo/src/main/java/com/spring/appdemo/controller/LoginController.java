package com.spring.appdemo.controller;

import com.spring.appdemo.payload.LoginRequest;
import com.spring.appdemo.payload.AuthResponse;  // Assuming AuthResponse is the correct response type
import com.spring.appdemo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login-v2") // Specific login endpoint
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Assuming AuthenticationService is handling login logic and returning an AuthResponse object
        AuthResponse response = authenticationService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
