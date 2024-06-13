package com.nortic.posdash.es.users.service;

import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.us.models.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Get user From DB by ID.
     * @param id
     * @return
     * @throws CustomException
     */
    UserDTO getUserById(UUID id) throws CustomException;

    /**
     * Get All Users List from DB.
     * @return
     * @throws CustomException
     */
    List<UserDTO> getAllUsers() throws CustomException;

    /**
     * Get Users From DB by page
     * @param size
     * @param page
     * @return
     * @throws CustomException
     */
    Page<UserDTO> getAllUsers(int size, int page) throws CustomException;
    /**
     * Update User record in DB.
     * @param id
     * @param user
     * @return
     * @throws CustomException
     */
    UserDTO updateUser(UUID id,UserDTO user) throws CustomException;

    /**
     * Delete user from DB.
     */
    void deleteById(UUID id) throws CustomException;
}
