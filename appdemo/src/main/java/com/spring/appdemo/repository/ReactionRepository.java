package com.spring.appdemo.repository;

import com.spring.appdemo.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findReactionsByPostId(Long postId);

    List<Reaction> findReactionsByCommentId(Long commentId);

    List<Reaction> findReactionsByUserId(Long userId);
}
