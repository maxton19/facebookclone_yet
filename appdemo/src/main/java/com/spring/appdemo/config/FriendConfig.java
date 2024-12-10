package com.spring.appdemo.config;

import com.spring.appdemo.dto.FriendDTO;
import com.spring.appdemo.mapper.FriendMapper;
import com.spring.appdemo.model.Friend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FriendConfig {

    @Bean
    public FriendMapper friendMapper() {
        return new FriendMapper() {
            @Override
            public FriendDTO toFriendDTO(Friend friend) {
                return null;
            }

            @Override
            public Friend toFriendEntity(FriendDTO friendDTO) {
                return null;
            }

            @Override
            public FriendDTO toDto(Friend friend) {
                // Implement the logic to map Friend to FriendDTO
                if (friend == null) {
                    return null;
                }

                FriendDTO friendDTO = new FriendDTO();
                friendDTO.setId(friend.getId());
                friendDTO.setUserId(friend.getUserId());
                friendDTO.setFriendId(friend.getFriendId());
                friendDTO.setCreatedDate(friend.getCreatedDate());  // Assuming 'createdDate' is part of the Friend model
                return friendDTO;
            }

            @Override
            public Friend toEntity(FriendDTO friendDTO) {
                // Implement the logic to map FriendDTO to Friend
                if (friendDTO == null) {
                    return null;
                }

                Friend friend = new Friend();
                friend.setId(friendDTO.getId());
                friend.setUserId(friendDTO.getUserId());
                friend.setFriendId(friendDTO.getFriendId());
                friend.setCreatedDate(friendDTO.getCreatedDate());  // Assuming 'createdDate' is part of the FriendDTO
                return friend;
            }
        }; // Replace this with actual initialization if needed
    }
}
