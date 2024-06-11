package com.nortic.posdash.es.users.service;

import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.us.models.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    /**
     * Add a new user to the DB.
     * @param user
     * @return
     */
    UserDTO addUser(UserDTO user) throws CustomException;
    /**
     * Delete user from DB.
     */
    void deleteById(UUID id) throws CustomException;
}
