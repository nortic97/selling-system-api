package com.nortic.pos.es.user.mapper;

import com.nortic.pos.es.user.dto.PaginatedUserDTO;
import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@RequiredArgsConstructor
public abstract class UserEntityMapper {

    public abstract UserDTO toUserDTO(UserEntity userEntity);

    public abstract UserEntity toUserEntity(UserDTO userDTO);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedUserDTO toPaginatedUserDTO(Page<UserEntity> page);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    public abstract void updateUserFromDTO(UserDTO userDTO, @MappingTarget UserEntity userEntity);
}
