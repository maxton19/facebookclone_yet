package com.spring.appdemo.mapper;

import com.spring.appdemo.dto.FriendDTO;
import com.spring.appdemo.model.Friend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    // Convert Friend entity to FriendDTO
    @Mapping(source = "user.id", target = "userId") // Assuming user_ should map to userId
    @Mapping(source = "friend.id", target = "friendId")
    FriendDTO toFriendDTO(Friend friend);

    // Convert FriendDTO to Friend entity
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "friendId", target = "friend.id")
    Friend toFriendEntity(FriendDTO friendDTO);

    FriendDTO toDto(Friend friend);

    Friend toEntity(FriendDTO friendDTO);
}
