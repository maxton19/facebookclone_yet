package com.spring.appdemo.repository;

import com.spring.appdemo.model.Friend;
import com.spring.appdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByUserAndFriend(User user, User friend);

    List<Friend> findByUserAndAcceptedFalse(User user);

    List<Friend> findByUserAndAcceptedTrue(User user);

    List<Friend> findByFriendAndAcceptedFalse(User friend);
}
