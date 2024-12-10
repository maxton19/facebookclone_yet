package com.spring.appdemo.mapper;

import com.spring.appdemo.model.Reaction;
import com.spring.appdemo.dto.ReactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReactionMapper {

    ReactionDTO toDto(Reaction reaction);

    // Convert a Reaction entity to ReactionDTO
    ReactionDTO toDTO(Reaction reaction);

    // Convert a ReactionDTO to Reaction entity
    Reaction toEntity(ReactionDTO reactionDTO);
}
