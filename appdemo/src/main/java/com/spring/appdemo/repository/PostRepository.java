package com.spring.appdemo.repository;

import com.spring.appdemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId); // Get posts by user
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId); // Get posts by user, ordered by creation time
}
