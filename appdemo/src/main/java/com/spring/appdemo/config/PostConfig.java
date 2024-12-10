package com.spring.appdemo.config;

import com.spring.appdemo.dto.PostDTO;
import com.spring.appdemo.mapper.PostMapper;
import com.spring.appdemo.model.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PostConfig {

    @Bean
    public PostMapper postMapper() {
        return new PostMapper() {
            @Override
            public PostDTO toDto(Post post) {
                // Implement the logic to map Post to PostDTO
                if (post == null) {
                    return null;
                }

                PostDTO postDTO = new PostDTO();
                postDTO.setId(post.getId());
                postDTO.setContent(post.getContent());
                postDTO.setAuthor(post.getAuthor());
                postDTO.setCreatedDate(post.getCreatedDate());  // Assuming 'createdDate' is part of Post model
                return postDTO;
            }

            @Override
            public Post toEntity(PostDTO postDTO) {
                // Implement the logic to map PostDTO to Post
                if (postDTO == null) {
                    return null;
                }

                Post post = new Post();
                post.setId(postDTO.getId());
                post.setContent(postDTO.getContent());
                post.setAuthor(postDTO.getAuthor());
                post.setCreatedDate(postDTO.getCreatedDate());  // Assuming 'createdDate' is part of PostDTO
                return post;
            }

            @Override
            public List<PostDTO> toDtoList(List<Post> posts) {
                return List.of();
            }

            @Override
            public List<Post> toEntityList(List<PostDTO> postDTOs) {
                return List.of();
            }

            @Override
            public String handleNullContent(String content) {
                return PostMapper.super.handleNullContent(content);
            }
        }; // Replace this with actual initialization if needed
    }
}
