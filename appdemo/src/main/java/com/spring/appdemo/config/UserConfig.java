package com.spring.appdemo.config;

import com.spring.appdemo.dto.PostDTO;
import com.spring.appdemo.dto.UserDTO;
import com.spring.appdemo.dto.UserRegistrationDTO;
import com.spring.appdemo.model.Post;
import com.spring.appdemo.model.User;
import com.spring.appdemo.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapper() {
            @Override
            public PostDTO toDto(Post post) {
                return null; // You can implement post mapping here if needed.
            }

            @Override
            public UserDTO toDto(User user) {
                if (user == null) {
                    return null;
                }

                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setUsername(user.getUsername());
                userDTO.setEmail(user.getEmail());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                userDTO.setGender(user.getGender());
                userDTO.setDateOfBirth(user.getDateOfBirth()); // No need to convert if LocalDate
                userDTO.setBio(user.getBio());
                userDTO.setProfilePic(user.getProfilePic());
                return userDTO;
            }

            @Override
            public User toEntity(UserDTO userDTO) {
                if (userDTO == null) {
                    return null;
                }

                User user = new User();
                user.setId(userDTO.getId());
                user.setUsername(userDTO.getUsername());
                user.setEmail(userDTO.getEmail());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setGender(userDTO.getGender());
                user.setDateOfBirth(userDTO.getDateOfBirth()); // No need to convert if LocalDate
                user.setBio(userDTO.getBio());
                user.setProfilePic(userDTO.getProfilePic());
                return user;
            }

            @Override
            public User toEntity(UserRegistrationDTO userRegistrationDTO) {
                if (userRegistrationDTO == null) {
                    return null;
                }

                User user = new User();
                user.setUsername(userRegistrationDTO.getUsername());
                user.setEmail(userRegistrationDTO.getEmail());
                user.setFirstName(userRegistrationDTO.getFirstName());
                user.setLastName(userRegistrationDTO.getLastName());
                user.setGender(userRegistrationDTO.getGender());
                user.setDateOfBirth(userRegistrationDTO.getDateOfBirth()); // Assuming date is already LocalDate
                user.setBio(userRegistrationDTO.getBio());
                user.setProfilePic(userRegistrationDTO.getProfilePic());
                return user;
            }
        };
    }
}
