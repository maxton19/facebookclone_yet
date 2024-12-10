package com.spring.appdemo.service;

import com.spring.appdemo.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostById(Long id);
    List<PostDTO> getAllPosts();
    List<PostDTO> getPostsByUserId(Long userId);
    PostDTO updatePost(Long id, PostDTO postDTO);
    boolean deletePost(Long id);
    List<PostDTO> getHomePagePosts(Long userId);  // New method for home page
}
