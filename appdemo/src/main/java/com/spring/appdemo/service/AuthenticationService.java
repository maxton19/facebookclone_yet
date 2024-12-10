package com.spring.appdemo.service;

import com.spring.appdemo.payload.AuthRequest;
import com.spring.appdemo.payload.AuthResponse;
import com.spring.appdemo.payload.LoginRequest;
import com.spring.appdemo.payload.RegisterRequest;
import com.spring.appdemo.security.JwtTokenUtil;
import com.spring.appdemo.model.User;
import com.spring.appdemo.repository.UserRepository;
import com.spring.appdemo.exception.UserAlreadyExistsException;
import com.spring.appdemo.exception.IncorrectCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    public String registerUser(RegisterRequest registerRequest) {
        // Check if user already exists by username
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists!");
        }

        // Check if user already exists by email
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists!");
        }

        // Create a new user object and encode the password before saving it
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encode the password
        user.setEmail(registerRequest.getEmail());

        // Save the user to the repository
        userRepository.save(user);

        return "User registered successfully!";
    }

    // Authenticate the user and generate JWT token
    public AuthResponse authenticateUser(AuthRequest authRequest) {
        try {
            // Authenticate the user with the given username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Generate JWT token if authentication is successful
            String token = jwtTokenUtil.generateToken(authentication.getName());
            return new AuthResponse(token);
        } catch (Exception e) {
            // Throw exception if credentials are invalid
            throw new IncorrectCredentialsException("Invalid username or password.");
        }
    }

    // Refresh the JWT token if expired
    public AuthResponse refreshToken(String expiredToken) {
        try {
            // Generate a new token using the expired one
            String newToken = jwtTokenUtil.refreshToken(expiredToken);
            return new AuthResponse(newToken);
        } catch (Exception e) {
            // Handle error during token refresh
            throw new RuntimeException("Error refreshing token: " + e.getMessage());
        }
    }

    // Login method to authenticate and generate a JWT token
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate the user with the provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Generate a new JWT token
            String token = jwtTokenUtil.generateToken(authentication.getName());

            // Return the generated token
            return new AuthResponse(token);
        } catch (Exception e) {
            // If credentials are incorrect, throw an exception
            throw new IncorrectCredentialsException("Invalid username or password.");
        }
    }
}
