package com.nortic.pos.es.user.mapper;

import com.nortic.pos.es.user.model.dto.PaginatedUserResponseDTO;
import com.nortic.pos.es.user.model.dto.UserDTO;
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

    @Mapping(
            target = "username",
            expression = "java(normalizeString(userDTO.getUsername()))"
    )
    @Mapping(
            target = "email",
            expression = "java(normalizeString(userDTO.getEmail()))"
    )
    @Mapping(
            target = "firstName",
            expression = "java(normalizeString(userDTO.getFirstName()))"
    )
    @Mapping(
            target = "lastName",
            expression = "java(normalizeString(userDTO.getLastName()))"
    )
    public abstract UserEntity toUserEntity(UserDTO userDTO);

    @Mapping(target = "number", expression = "java(page.getNumber() + 1)")
    public abstract PaginatedUserResponseDTO toPaginatedUserDTO(Page<UserEntity> page);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    public abstract void updateUserFromDTO(UserDTO userDTO, @MappingTarget UserEntity userEntity);

    protected String normalizeString(String value) {

        if (value == null) {
            return null;
        }

        return value.trim().toLowerCase();

    }
}
