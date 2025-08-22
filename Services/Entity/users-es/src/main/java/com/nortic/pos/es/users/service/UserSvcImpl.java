package com.nortic.pos.es.users.service;

import com.nortic.pos.es.users.repository.UserRepository;
import com.nortic.pos.es.users.util.RestMapper;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSvcImpl implements IUserSvc {

    private final UserRepository userRepository;
    private final RestMapper restMapper;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUser(UUID id) {
        return null;
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public void softDeleteUser(UUID id) {

    }

    @Override
    public void changeUserStatus(UUID id, Boolean status) {

    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable, UUID storeId, UUID branchId, UUID roleId, AccountTypeEnum accountType, Boolean isActive, String search) {
        return null;
    }

    @Override
    public Page<UserDTO> getDeletedUsers(Pageable pageable, UUID storeId, UUID branchId, UUID roleId, AccountTypeEnum accountType, Boolean isActive, String search) {
        return null;
    }
}
