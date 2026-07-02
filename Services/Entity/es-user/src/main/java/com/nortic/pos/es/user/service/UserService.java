package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.dto.PaginatedUserDTO;
import com.nortic.pos.es.user.dto.ParamsDTO;
import com.nortic.pos.es.user.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO, ParamsDTO paramsDTO);

    void deleteUser(ParamsDTO paramsDTO);

    PaginatedUserDTO getAllUsers(ParamsDTO paramsDTO);

    UserDTO getUserById(ParamsDTO paramsDTO);

    // queryUsers

    UserDTO restoreUser(ParamsDTO paramsDTO);

    // searchUsers

    UserDTO updateUser(ParamsDTO paramsDTO, UserDTO userDTO);
}
