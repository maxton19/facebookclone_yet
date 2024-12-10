package com.spring.appdemo.mapper;

import com.spring.appdemo.dto.PostDTO;
import com.spring.appdemo.dto.UserDTO;
import com.spring.appdemo.dto.UserRegistrationDTO;
import com.spring.appdemo.model.Post;
import com.spring.appdemo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Map Post entity to PostDTO
    PostDTO toDto(Post post);

    // Map User entity to UserDTO
    UserDTO toDto(User user);

    // Map UserDTO to User entity
    User toEntity(UserDTO userDTO);

    // Map UserRegistrationDTO to User entity
    User toEntity(UserRegistrationDTO userRegistrationDTO);
}
