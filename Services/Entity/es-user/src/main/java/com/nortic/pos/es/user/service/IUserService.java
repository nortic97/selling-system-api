package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.dto.UserDTO;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);
}
