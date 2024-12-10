package com.spring.appdemo.dto;

public class ReactionDTO {
    private Long id;
    private String type; // "like", "love", etc.
    private Long userId;
    private Long postId;
    private Long commentId;

    public void setId(Object id) {
        this.id = (Long) id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public void setPostId(Long id) {
        this.postId = id;
    }

    public void setCommentId(Long id) {
        this.commentId = id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getType() {
        return type;
    }

    public Long getCommentId() {
        return commentId;
    }

    // Getters and Setters
}
