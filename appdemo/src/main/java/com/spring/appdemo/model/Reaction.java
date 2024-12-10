package com.spring.appdemo.model;

import jakarta.persistence.*;

@Entity
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType type; // "like", "love", etc.

    @ManyToOne
    @JoinColumn(name = "users_")
    private User user;

    @ManyToOne
    @JoinColumn(name = "_post", nullable = true)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comments_", nullable = true)
    private Comment comment;

    // Constructors
    public Reaction() {}

    public Reaction(ReactionType type, User user, Post post, Comment comment) {
        this.type = type;
        this.user = user;
        this.post = post;
        this.comment = comment;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
