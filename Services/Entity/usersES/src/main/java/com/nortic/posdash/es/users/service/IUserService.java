package com.nortic.posdash.es.users.service;

import com.nortic.posdash.us.models.dto.UserDTO;

public interface IUserService {
    /**
     * Add a new user to the DB.
     * @param user
     * @return
     */
    public UserDTO addUser(UserDTO user);
}
