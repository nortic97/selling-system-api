package com.nortic.pos.es.user.mapper;

import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.rest.gen.model.RestModelCreateUserRequestDTO;
import com.nortic.pos.es.user.rest.gen.model.RestModelUpdateUserRequestDTO;
import com.nortic.pos.es.user.rest.gen.model.RestModelUserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public abstract class UserRestMapper {


    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract UserDTO toUserDTO(RestModelCreateUserRequestDTO user);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract UserDTO toUserDTO(RestModelUpdateUserRequestDTO user);

    public abstract RestModelUserResponseDTO toRestModelUserResponseDTO(UserDTO user);

}
