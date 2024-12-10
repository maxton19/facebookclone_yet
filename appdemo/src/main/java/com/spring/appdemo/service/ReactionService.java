package com.spring.appdemo.service;

import com.spring.appdemo.dto.ReactionDTO;
import com.spring.appdemo.mapper.ReactionMapper;
import com.spring.appdemo.model.Reaction;
import com.spring.appdemo.model.Post;
import com.spring.appdemo.model.Comment;
import com.spring.appdemo.model.ReactionType;
import com.spring.appdemo.repository.ReactionRepository;
import com.spring.appdemo.repository.PostRepository;
import com.spring.appdemo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ReactionMapper reactionMapper;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository, PostRepository postRepository,
                           CommentRepository commentRepository, ReactionMapper reactionMapper) {
        this.reactionRepository = reactionRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.reactionMapper = reactionMapper;
    }

    /**
     * Add a reaction to a post or comment.
     *
     * @param reactionDTO DTO containing the reaction details.
     * @return The created Reaction DTO.
     */
    @Transactional
    public ReactionDTO addReaction(ReactionDTO reactionDTO) {
        // Ensure that either postId or commentId is set
        if (reactionDTO.getPostId() == null && reactionDTO.getCommentId() == null) {
            throw new IllegalArgumentException("Either postId or commentId must be provided.");
        }

        if (reactionDTO.getPostId() != null && reactionDTO.getCommentId() != null) {
            throw new IllegalArgumentException("Cannot specify both postId and commentId.");
        }

        // Convert String to ReactionType enum
        ReactionType reactionType;
        try {
            reactionType = ReactionType.valueOf(reactionDTO.getType().toUpperCase()); // Converts to enum
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid reaction type: " + reactionDTO.getType());
        }

        // Find the target entity (Post or Comment) by ID
        Optional<Post> post = Optional.ofNullable(reactionDTO.getPostId())
                .flatMap(postRepository::findById);
        Optional<Comment> comment = Optional.ofNullable(reactionDTO.getCommentId())
                .flatMap(commentRepository::findById);

        // Handle invalid post or comment
        if (post.isEmpty() && comment.isEmpty()) {
            throw new IllegalArgumentException("Post or Comment not found.");
        }

        // Create a new Reaction entity
        Reaction reaction = new Reaction();
        reaction.setType(reactionType);  // Set the type as ReactionType enum

        // If it's a post reaction
        post.ifPresent(reaction::setPost);

        // If it's a comment reaction
        comment.ifPresent(reaction::setComment);

        // Save the reaction to the repository
        reactionRepository.save(reaction);

        // Return the DTO
        return reactionMapper.toDTO(reaction);
    }

    /**
     * Remove a reaction.
     *
     * @param reactionId The ID of the reaction to be removed.
     */
    @Transactional
    public void removeReaction(Long reactionId) {
        Reaction reaction = reactionRepository.findById(reactionId)
                .orElseThrow(() -> new IllegalArgumentException("Reaction not found with ID: " + reactionId));

        // Delete the reaction
        reactionRepository.delete(reaction);
    }
}
