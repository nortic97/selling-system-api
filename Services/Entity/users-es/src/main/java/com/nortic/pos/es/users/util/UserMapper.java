package com.nortic.pos.es.users.util;

import com.nortic.pos.es.users.model.RestModelUser;
import com.nortic.pos.models.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(RestModelUser user);
    RestModelUser toRestModel(UserDTO userDTO);
}
