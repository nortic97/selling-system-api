package com.nortic.posdash.es.users.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.models.dto.UserDTO;

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

    /**
     * Get a list of users deleted.
     * @return
     * @throws CustomException
     */
    Page<UserDTO> getDeletedUsers(int page, int size) throws CustomException;

    /**
     * delete permanently a user;
     * @param id
     * @throws CustomException
     */
    void permanentlyDeleteUser(UUID id) throws CustomException;
}
