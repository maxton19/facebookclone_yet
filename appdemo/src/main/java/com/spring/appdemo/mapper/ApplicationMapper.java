package com.spring.appdemo.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper<T, E> {

    // Maps an entity to a DTO
    T toDto(E entity);

    // Maps a DTO to an entity
    E toEntity(T entity);

}
