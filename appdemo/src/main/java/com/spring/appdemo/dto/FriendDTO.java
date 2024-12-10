package com.spring.appdemo.dto;

import java.time.LocalDateTime;

public class FriendDTO {

    private Long id;
    private Long userId;
    private Long friendId;
    private boolean accepted;  // Renamed for clarity

    public FriendDTO() {
    }

    public FriendDTO(Long id, Long userId, Long friendId, boolean accepted) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.accepted = accepted;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
    }

    public Object getCreatedDate() {
        return null;
    }
}
