package com.spring.appdemo.repository;

import com.spring.appdemo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId); // Get comments by post
    List<Comment> findByUserId(Long userId); // Get comments by user

    Optional<Object> findByIdAndPostId(Long commentId, Long postId);
}
