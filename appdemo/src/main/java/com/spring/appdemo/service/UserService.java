package com.spring.appdemo.service;

import com.spring.appdemo.dto.UserDTO;
import com.spring.appdemo.dto.UserRegistrationDTO;
import com.spring.appdemo.model.User;
import com.spring.appdemo.repository.UserRepository;
import com.spring.appdemo.mapper.UserMapper;
import com.spring.appdemo.exception.UserRegistrationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // Using PasswordEncoder interface
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * Register a new user.
     *
     * @param userRegistrationDTO Data for user registration.
     * @return Registered user details.
     * @throws UserRegistrationException if email is already registered.
     */
    @Transactional
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Validate if the email already exists
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new UserRegistrationException("Email is already registered");
        }

        // Convert UserRegistrationDTO to User entity using UserMapper
        User user = userMapper.toEntity(userRegistrationDTO);

        // Encrypt password
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        // Save the user in the database
        User savedUser = userRepository.save(user);

        // Convert the saved user back to DTO to return
        return userMapper.toDto(savedUser);
    }

    /**
     * Get user by ID.
     *
     * @param id The user ID.
     * @return Optional<UserDTO> if found, empty otherwise.
     */
    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDto);
    }

    // Other CRUD operations can go here...
}
