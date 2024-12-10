package com.spring.appdemo.mapper;

import com.spring.appdemo.dto.CommentDTO;
import com.spring.appdemo.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO commentDTO);
}
