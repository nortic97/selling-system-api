package com.nortic.pos.es.users.rest.controller;

import com.nortic.pos.es.users.controller.UsersApi;
import com.nortic.pos.es.users.model.RestModelAccountType;
import com.nortic.pos.es.users.model.RestModelChangeUserStatusRequest;
import com.nortic.pos.es.users.model.RestModelPaginatedUsers;
import com.nortic.pos.es.users.model.RestModelUser;
import com.nortic.pos.es.users.service.IUserSvc;
import com.nortic.pos.es.users.util.RestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final IUserSvc userSvc;
    private final RestMapper restMapper;


    @Override
    public ResponseEntity<RestModelUser> changeUserStatus(UUID id, RestModelChangeUserStatusRequest restModelChangeUserStatusRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUser> createUser(RestModelUser restModelUser) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPaginatedUsers> getAllUsers(Integer page, Integer size, String sort, UUID storeId, UUID branchId, UUID roleId, RestModelAccountType accountType, Boolean isActive, String search) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPaginatedUsers> getDeletedUsers(Integer page, Integer size, String sort, UUID storeId, UUID branchId, UUID roleId, RestModelAccountType accountType, Boolean isActive, String search) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUser> getUserById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> permanentDeleteUser(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUser> updateUser(UUID id, RestModelUser restModelUser) {
        return null;
    }
}
