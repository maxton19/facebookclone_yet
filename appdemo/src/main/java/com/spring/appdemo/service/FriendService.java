package com.spring.appdemo.service;

import com.spring.appdemo.dto.FriendDTO;
import com.spring.appdemo.mapper.FriendMapper;
import com.spring.appdemo.model.Friend;
import com.spring.appdemo.model.User;
import com.spring.appdemo.repository.FriendRepository;
import com.spring.appdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendMapper friendMapper;

    // Send a friend request
    public FriendDTO sendFriendRequest(Long userId, Long friendId) {
        if (userId.equals(friendId)) {
            throw new IllegalArgumentException("Cannot send a friend request to yourself.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found."));

        // Check if a relationship already exists
        Optional<Friend> existingRequest = friendRepository.findByUserAndFriend(user, friend);
        if (existingRequest.isPresent()) {
            throw new RuntimeException("Friend request already exists.");
        }

        Friend friendRequest = new Friend(user, friend, false);
        Friend savedFriendRequest = friendRepository.save(friendRequest);
        return friendMapper.toFriendDTO(savedFriendRequest);
    }

    // Accept a friend request
    public FriendDTO acceptFriendRequest(Long userId, Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found."));

        Friend friendRequest = friendRepository.findByUserAndFriend(friend, user)
                .orElseThrow(() -> new RuntimeException("Friend request not found."));

        friendRequest.setAccepted(true);
        Friend updatedFriendRequest = friendRepository.save(friendRequest);
        return friendMapper.toFriendDTO(updatedFriendRequest);
    }

    // Reject a friend request
    public void rejectFriendRequest(Long userId, Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found."));

        Friend friendRequest = friendRepository.findByUserAndFriend(friend, user)
                .orElseThrow(() -> new RuntimeException("Friend request not found."));

        friendRepository.delete(friendRequest);
    }

    // Get all friends of a user
    public List<FriendDTO> getAllFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        List<Friend> friends = friendRepository.findByUserAndAcceptedTrue(user);
        return friends.stream().map(friendMapper::toFriendDTO).collect(Collectors.toList());
    }

    // Get all friend requests sent by a user
    public List<FriendDTO> getSentFriendRequests(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        List<Friend> sentRequests = friendRepository.findByUserAndAcceptedFalse(user);
        return sentRequests.stream().map(friendMapper::toFriendDTO).collect(Collectors.toList());
    }

    // Get all friend requests received by a user
    public List<FriendDTO> getReceivedFriendRequests(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        List<Friend> receivedRequests = friendRepository.findByFriendAndAcceptedFalse(user);
        return receivedRequests.stream().map(friendMapper::toFriendDTO).collect(Collectors.toList());
    }

    // Check if two users are friends
    public boolean areFriends(Long userId, Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found."));

        return friendRepository.findByUserAndFriend(user, friend)
                .map(Friend::isAccepted)
                .orElse(false);
    }
}
