package com.nortic.pos.es.users.service;

import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserSvc {
    
    /**
     * Create a new user
     */
    UserDTO createUser(UserDTO userDTO);
    
    /**
     * Get user by ID (only active users)
     */
    UserDTO getUser(UUID id);
    
    /**
     * Update user by ID
     */
    UserDTO updateUser(UUID id, UserDTO userDTO);
    
    /**
     * Permanently delete user by ID
     */
    void deleteUser(UUID id);
    
    /**
     * Soft delete user by ID
     */
    void softDeleteUser(UUID id);
    
    /**
     * Change user active status
     */
    void changeUserStatus(UUID id, Boolean status);
    
    /**
     * Get all ACTIVE users with dynamic filtering and pagination
     * All parameters are optional - null values are ignored in filtering
     * 
     * @param pageable Pagination and sorting information
     * @param storeId Filter by store ID (optional - null returns all stores)
     * @param branchId Filter by branch ID (optional - null returns all branches)
     * @param roleId Filter by role ID (optional - null returns all roles)
     * @param accountType Filter by account type (optional - null returns all types)
     * @param isActive Filter by active status (optional - null returns all statuses)
     * @param search Search in firstName, lastName, email, userName (optional - empty returns all)
     * @return Page of active users matching the filters
     */
    Page<UserDTO> getAllUsers(
            Pageable pageable, 
            UUID storeId, 
            UUID branchId, 
            UUID roleId, 
            AccountTypeEnum accountType, 
            Boolean isActive, 
            String search
    );
    
    /**
     * Get all DELETED users with the SAME dynamic filtering and pagination as getAllUsers
     * This ensures consistency between both methods - same filters, same behavior
     * All parameters are optional - null values are ignored in filtering
     * 
     * @param pageable Pagination and sorting information
     * @param storeId Filter by store ID (optional - null returns all stores)
     * @param branchId Filter by branch ID (optional - null returns all branches)
     * @param roleId Filter by role ID (optional - null returns all roles)
     * @param accountType Filter by account type (optional - null returns all types)
     * @param isActive Filter by active status (optional - null returns all statuses)
     * @param search Search in firstName, lastName, email, userName (optional - empty returns all)
     * @return Page of deleted users matching the filters
     */
    Page<UserDTO> getDeletedUsers(
            Pageable pageable, 
            UUID storeId, 
            UUID branchId, 
            UUID roleId, 
            AccountTypeEnum accountType, 
            Boolean isActive, 
            String search
    );
}
