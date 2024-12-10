package com.spring.appdemo.service;

import com.spring.appdemo.dto.CommentDTO;

import java.util.List;

public interface CommentServiceInterface {

    CommentDTO addComment(Long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentsByPostId(Long postId);

    CommentDTO getCommentById(Long id);

    void deleteComment(Long id);

    CommentDTO createComment(Long postId, CommentDTO commentDTO);

    boolean deleteComment(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO);

    List<CommentDTO> getAllComments(Long postId);

    CommentDTO getCommentById(Long postId, Long commentId);
}
