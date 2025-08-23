package com.nortic.pos.es.users.rest.controller;

import com.nortic.pos.es.users.controller.UsersApi;
import com.nortic.pos.es.users.model.RestModelChangeUserStatusRequest;
import com.nortic.pos.es.users.model.RestModelPaginatedUsers;
import com.nortic.pos.es.users.model.RestModelUser;
import com.nortic.pos.es.users.service.UserSvcImpl;
import com.nortic.pos.es.users.util.RestMapper;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final RestMapper restMapper;
    private final UserSvcImpl userSvc;

    @Override
    public ResponseEntity<RestModelUser> createUser(RestModelUser restModelUser) {
        log.info("Create User Rest Controller Start...");
        UserDTO userDTO = userSvc.createUser(restMapper.restModelToDTO(restModelUser));
        log.info("Create User Rest Controller End...");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restMapper.dtoToRestModel(userDTO));
    }

    @Override
    public ResponseEntity<RestModelUser> getUserById(UUID id) {
        log.info("Get User By Id Rest Controller Start...");
        RestModelUser user = restMapper.dtoToRestModel(userSvc.getUser(id));
        log.info("Get User By Id Rest Controller End...");
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<RestModelUser> updateUser(UUID id, RestModelUser restModelUser) {
        log.info("Update User Rest Controller Start...");
        UserDTO userDTO = userSvc.updateUser(id, restMapper.restModelToDTO(restModelUser));
        log.info("Update User Rest Controller End...");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restMapper.dtoToRestModel(userDTO));
    }

    @Override
    public ResponseEntity<Void> permanentDeleteUser(UUID id) {
        log.info("Delete User Rest Controller Start...");
        userSvc.deleteUser(id);
        log.info("Delete User Rest Controller End...");
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        log.info("Soft Delete User Rest Controller Start...");
        userSvc.softDeleteUser(id);
        log.info("Soft Delete User Rest Controller End...");
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<RestModelUser> changeUserStatus(UUID id, RestModelChangeUserStatusRequest restModelChangeUserStatusRequest) {
        log.info("Change User Status Rest Controller Start...");
        userSvc.changeUserStatus(id, restModelChangeUserStatusRequest.getIsActive());
        log.info("Change User Status Rest Controller End...");
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<RestModelPaginatedUsers> getAllUsers(Integer page, Integer size, String sort, UUID storeId, UUID branchId, UUID roleId, String accountType, Boolean isActive, String search) {
        log.info("Get All Users Rest Controller Start...");
        
        Pageable pageable = createPageable(page, size, sort);
        AccountTypeEnum accountTypeEnum = accountType != null ? AccountTypeEnum.fromValue(accountType) : null;
        
        Page<UserDTO> result = userSvc.getAllUsers(pageable, storeId, branchId, roleId, accountTypeEnum, isActive, search);
        RestModelPaginatedUsers response = restMapper.pageToRestModelPaginatedUsers(result);
        
        log.info("Get All Users Rest Controller End...");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RestModelPaginatedUsers> getDeletedUsers(Integer page, Integer size, String sort, UUID storeId, UUID branchId, UUID roleId, String accountType, Boolean isActive, String search) {
        log.info("Get Deleted Users Rest Controller Start...");
        
        Pageable pageable = createPageable(page, size, sort);
        AccountTypeEnum accountTypeEnum = accountType != null ? AccountTypeEnum.fromValue(accountType) : null;
        
        Page<UserDTO> result = userSvc.getDeletedUsers(pageable, storeId, branchId, roleId, accountTypeEnum, isActive, search);
        RestModelPaginatedUsers response = restMapper.pageToRestModelPaginatedUsers(result);
        
        log.info("Get Deleted Users Rest Controller End...");
        return ResponseEntity.ok(response);
    }

    private Pageable createPageable(Integer page, Integer size, String sort) {

        int pageNumber = (page != null && page > 0) ? page - 1 : 0;
        int pageSize = size != null ? size : 10;
        
        if (sort != null && !sort.trim().isEmpty()) {
            String[] sortParts = sort.split(",");
            String sortField = sortParts[0];
            Sort.Direction direction = sortParts.length > 1 && "desc".equalsIgnoreCase(sortParts[1]) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
            return PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField));
        }
        
        return PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "createdAt"));
    }
}
