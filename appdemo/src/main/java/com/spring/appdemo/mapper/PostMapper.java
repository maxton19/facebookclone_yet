package com.spring.appdemo.mapper;

import com.spring.appdemo.dto.PostDTO;
import com.spring.appdemo.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    // Mapping for a single Post entity to PostDTO
    @Mapping(source = "content", target = "body", qualifiedByName = "handleNullContent")
    PostDTO toDto(Post post);

    // Mapping for a PostDTO back to Post entity
    @Mapping(source = "body", target = "content")
    Post toEntity(PostDTO postDTO);

    // Mapping for a list of Post entities to PostDTOs
    List<PostDTO> toDtoList(List<Post> posts);

    // Mapping for a list of PostDTOs back to Post entities
    List<Post> toEntityList(List<PostDTO> postDTOs);

    /**
     * Custom mapping method to handle null content in the Post entity.
     *
     * @param content The content of the post.
     * @return Non-null content or a default message.
     */
    @Named("handleNullContent")
    default String handleNullContent(String content) {
        return content == null ? "No content available" : content;
    }
}
