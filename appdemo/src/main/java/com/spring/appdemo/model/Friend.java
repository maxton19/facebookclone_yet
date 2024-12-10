package com.spring.appdemo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    @Column(name = "accepted", nullable = false) // Explicitly naming the column
    private boolean accepted;

    // Default constructor
    public Friend() {
    }

    // Parameterized constructor
    public Friend(User user, User friend, boolean accepted) {
        this.user = user;
        this.friend = friend;
        this.accepted = accepted;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Long getUserId() {
        return user.getId();
    }

    public Long getFriendId() {
        return friend.getId();
    }

    public LocalDateTime getCreatedDate() {
        return getCreatedDate();

    }

    public void setCreatedDate(Object createdDate) {
        this.setCreatedDate(createdDate);
    }

    public void setFriendId(Long friendId) {
        this.friend.setId(friendId);
    }

    public void setUserId(Long userId) {
        this.user.setId(userId);
    }
}
