package com.spring.appdemo.config;

import com.spring.appdemo.dto.CommentDTO;
import com.spring.appdemo.mapper.CommentMapper;
import com.spring.appdemo.model.Comment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {

    @Bean
    public CommentMapper commentMapper() {
        return new CommentMapper() {
            @Override
            public CommentDTO toDto(Comment comment) {
                // Implement the logic to map Comment to CommentDTO
                return null;
            }

            @Override
            public Comment toEntity(CommentDTO commentDTO) {
                // Implement the logic to map CommentDTO to Comment
                return null;
            }
        }; // Replace this with actual implementation as needed
    }
}
