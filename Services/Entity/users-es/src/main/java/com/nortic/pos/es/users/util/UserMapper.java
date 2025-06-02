package com.nortic.pos.es.users.util;

import com.nortic.pos.es.users.rest.gen.model.RestModelUser;
import com.nortic.posdash.models.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(RestModelUser user);
    RestModelUser toRestModel(UserDTO userDTO);
}
