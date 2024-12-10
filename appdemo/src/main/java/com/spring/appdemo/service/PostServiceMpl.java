package com.spring.appdemo.service;

import com.spring.appdemo.dto.PostDTO;
import com.spring.appdemo.exception.ResourceNotFoundException;
import com.spring.appdemo.mapper.PostMapper;
import com.spring.appdemo.model.Post;
import com.spring.appdemo.model.User;
import com.spring.appdemo.repository.PostRepository;
import com.spring.appdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceMpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;  // Inject UserRepository

    public PostServiceMpl(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        if (postDTO == null) {
            throw new IllegalArgumentException("Post data cannot be null");
        }
        Post post = postMapper.toEntity(postDTO);
        Post savedPost = postRepository.save(post);
        return postMapper.toDto(savedPost);
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = findPostById(id);
        return postMapper.toDto(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return postRepository.findByUserId(userId).stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        if (postDTO == null) {
            throw new IllegalArgumentException("Post data cannot be null");
        }
        Post post = findPostById(id);
        if (postDTO.getTitle() != null) {
            post.setTitle(postDTO.getTitle());
        }
        if (postDTO.getContent() != null) {
            post.setContent(postDTO.getContent());
        }
        Post updatedPost = postRepository.save(post);
        return postMapper.toDto(updatedPost);
    }

    @Override
    public boolean deletePost(Long id) {
        Post post = findPostById(id);
        postRepository.delete(post);
        return true;  // Return true indicating successful deletion
    }

    @Override
    public List<PostDTO> getHomePagePosts(Long userId) {
        // Fetching all posts for simplicity, you can modify this based on your business needs (like posts from friends, etc.)
        List<Post> posts = postRepository.findAll();  // Modify to filter by friends, etc. if needed
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    // Private helper method to find a post by ID and handle exceptions
    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    // Private helper method to find a User by ID and handle exceptions
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }
}
