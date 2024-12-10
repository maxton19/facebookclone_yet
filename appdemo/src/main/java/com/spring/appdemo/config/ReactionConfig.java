package com.spring.appdemo.config;

import com.spring.appdemo.dto.ReactionDTO;
import com.spring.appdemo.mapper.ReactionMapper;
import com.spring.appdemo.model.Reaction;
import com.spring.appdemo.model.ReactionType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReactionConfig {

    @Bean
    public ReactionMapper reactionMapper() {
        return new ReactionMapper() {
            @Override
            public ReactionDTO toDto(Reaction reaction) {
                if (reaction == null) {
                    return null;
                }

                ReactionDTO reactionDTO = new ReactionDTO();
                reactionDTO.setId(reaction.getId());
                reactionDTO.setType(reaction.getType().name());  // Enum to String
                reactionDTO.setUserId(reaction.getUser().getId());  // Assuming Reaction has a User
                if (reaction.getPost() != null) {
                    reactionDTO.setPostId(reaction.getPost().getId());  // If there is a Post
                }
                if (reaction.getComment() != null) {
                    reactionDTO.setCommentId(reaction.getComment().getId());  // If there is a Comment
                }
                return reactionDTO;
            }

            @Override
            public ReactionDTO toDTO(Reaction reaction) {
                return null;
            }

            @Override
            public Reaction toEntity(ReactionDTO reactionDTO) {
                if (reactionDTO == null) {
                    return null;
                }

                Reaction reaction = new Reaction();
                reaction.setId(reactionDTO.getPostId());
                reaction.setType(ReactionType.valueOf(reactionDTO.getType())); // String to Enum
                // You may need to map User, Post, and Comment objects here as well.
                return reaction;
            }
        };
    }
}
