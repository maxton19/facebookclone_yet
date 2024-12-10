package com.spring.appdemo.service;

import com.spring.appdemo.dto.CommentDTO;
import com.spring.appdemo.mapper.CommentMapper;
import com.spring.appdemo.model.Comment;
import com.spring.appdemo.model.Post;
import com.spring.appdemo.repository.CommentRepository;
import com.spring.appdemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements CommentServiceInterface {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * Add a comment to a specific post.
     *
     * @param postId    The ID of the post.
     * @param commentDTO The comment to add.
     * @return The added comment as a DTO.
     */
    @Override
    public CommentDTO addComment(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setPost(post);  // Associate the comment with the post
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    /**
     * Get all comments for a specific post.
     *
     * @param postId The ID of the post.
     * @return A list of comments for the given post.
     */
    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Get a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return The comment as a DTO.
     */
    @Override
    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return commentMapper.toDto(comment);
    }

    /**
     * Delete a comment by its ID.
     *
     * @param id The ID of the comment to delete.
     */
    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        return null;
    }

    @Override
    public boolean deleteComment(Long postId, Long commentId) {
        return false;
    }

    /**
     * Update a comment. (To be implemented as needed)
     *
     * @param postId    The ID of the post.
     * @param commentId The ID of the comment to update.
     * @param commentDTO The new data for the comment.
     * @return The updated comment as a DTO.
     */
    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        // Fetch the comment by ID
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Update the comment fields (this is a simple example, you might want to check and validate each field)
        comment.setContent(commentDTO.getContent());
        comment.setAuthor(commentDTO.getAuthor());

        // Save the updated comment
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDto(updatedComment);
    }

    @Override
    public List<CommentDTO> getAllComments(Long postId) {
        return List.of();
    }

    /**
     * Get a specific comment by its postId and commentId.
     *
     * @param postId    The ID of the post.
     * @param commentId The ID of the comment.
     * @return The comment as a DTO.
     */
    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Comment comment = (Comment) commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return commentMapper.toDto(comment);
    }
}
