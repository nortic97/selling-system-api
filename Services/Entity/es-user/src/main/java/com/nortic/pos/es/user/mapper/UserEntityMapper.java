package com.nortic.pos.es.user.mapper;

import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserEntityMapper {
    public abstract UserDTO toUserDTO(UserEntity userEntity);
    public abstract UserEntity toUserEntity(UserDTO userDTO);
}
