package com.nortic.pos.es.users.util;

import com.nortic.pos.es.users.model.RestModelUser;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestMapper {
    UserDTO restModelToDTO(RestModelUser restModelUser);
    UserEntity dtoToEntity(UserDTO userDTO);
    UserDTO entityToDTO(UserEntity userEntity);
    RestModelUser dtoToRestModel(UserDTO userDTO);
}
