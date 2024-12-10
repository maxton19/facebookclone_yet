package com.spring.appdemo.controller;

import com.spring.appdemo.dto.UserDTO;
import com.spring.appdemo.dto.UserRegistrationDTO;
import com.spring.appdemo.exception.UserRegistrationException;
import com.spring.appdemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class UserController {

    private final UserService userService;

    // Constructor injection (preferred over @Autowired)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register a new user.
     *
     * @param userRegistrationDTO Data for user registration.
     * @param bindingResult       Holds validation errors, if any.
     * @return Registered user details or error message.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {
        // Validate input fields and return errors if any
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages.toString());
        }

        try {
            // Attempt to register the user
            UserDTO registeredUser = userService.registerUser(userRegistrationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (UserRegistrationException ex) {
            // Handle user registration-specific errors (e.g., email already exists)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + ex.getMessage());
        } catch (Exception ex) {
            // Generic error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration.");
        }
    }
}
