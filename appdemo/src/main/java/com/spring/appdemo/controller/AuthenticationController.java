package com.spring.appdemo.controller;

import com.spring.appdemo.payload.AuthRequest;
import com.spring.appdemo.payload.AuthResponse;
import com.spring.appdemo.payload.RegisterRequest;
import com.spring.appdemo.service.AuthenticationService;
import com.spring.appdemo.exception.UserAlreadyExistsException;
import com.spring.appdemo.exception.IncorrectCredentialsException;
import com.spring.appdemo.exception.ExpiredTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow cross-origin requests if needed
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // User registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        logger.info("Attempting to register user with email: {}", registerRequest.getEmail());
        try {
            authenticationService.registerUser(registerRequest);
            logger.info("User registered successfully: {}", registerRequest.getEmail());
            return ResponseEntity.ok("User registered successfully!");
        } catch (UserAlreadyExistsException e) {
            logger.error("Registration failed for email {}: {}", registerRequest.getEmail(), e.getMessage());
            return ResponseEntity.badRequest().body("Username or email already exists.");
        } catch (Exception e) {
            logger.error("Unexpected error during registration: ", e);
            return ResponseEntity.internalServerError().body("Registration failed. Please try again later.");
        }
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        logger.info("User login attempt with username: {}", authRequest.getUsername());
        try {
            AuthResponse authResponse = authenticationService.authenticateUser(authRequest);
            logger.info("User logged in successfully: {}", authRequest.getUsername());
            return ResponseEntity.ok(authResponse);
        } catch (IncorrectCredentialsException e) {
            logger.error("Login failed for username {}: {}", authRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(401).body(new AuthResponse("Invalid username or password."));
        } catch (Exception e) {
            logger.error("Unexpected error during login for username {}: ", authRequest.getUsername(), e);
            return ResponseEntity.internalServerError().body(new AuthResponse("Login failed. Please try again later."));
        }
    }

    // Refresh the JWT token
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody String expiredToken) {
        logger.info("Token refresh attempt");
        if (expiredToken == null || expiredToken.isBlank()) {
            logger.error("Invalid token for refresh: Token is null or empty");
            return ResponseEntity.badRequest().body(new AuthResponse("Invalid token provided."));
        }

        try {
            AuthResponse authResponse = authenticationService.refreshToken(expiredToken);
            logger.info("Token refreshed successfully");
            return ResponseEntity.ok(authResponse);
        } catch (ExpiredTokenException e) {
            logger.error("Token refresh failed: {}", e.getMessage());
            return ResponseEntity.status(401).body(new AuthResponse("Refresh token is expired."));
        } catch (Exception e) {
            logger.error("Unexpected error during token refresh: ", e);
            return ResponseEntity.internalServerError().body(new AuthResponse("Error refreshing token. Please try again later."));
        }
    }
}
