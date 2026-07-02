package com.nortic.pos.es.user.controller;

import com.nortic.pos.es.user.dto.ParamsDTO;
import com.nortic.pos.es.user.mapper.UserRestMapper;
import com.nortic.pos.es.user.rest.gen.api.UserApi;
import com.nortic.pos.es.user.rest.gen.model.*;
import com.nortic.pos.es.user.service.UserService;
import com.nortic.pos.es.user.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserApi {

    private final UserRestMapper userRestMapper;
    private final UserService userService;

    @Override
    public ResponseEntity<RestModelUserResponseDTO> createUser(
            UUID correlationId,
            String consumerId,
            RestModelCreateUserRequestDTO restModelCreateUserRequestDTO
    ) {
        log.info("Creating user REST controller...");

        var user = userRestMapper.toUserDTO(restModelCreateUserRequestDTO);

        var params = ParamsDTO.builder().correlationId(correlationId).consumerId(consumerId).build();

        var createdUser = userService.createUser(user, params);

        log.info("Creating user REST Controller finished...");

        return ResponseEntity.ok(userRestMapper.toRestModelUserResponseDTO(createdUser));

    }

    @Override
    public ResponseEntity<Void> deleteUser(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {

        log.info("Deleting user REST controller...");

        var params = ParamsDTO.builder()
                .id(id)
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        userService.deleteUser(params);

        log.info("Deleting user REST controller finished...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedUserResponseDTO> getAllUsers(
            UUID correlationId,
            String consumerId,
            Integer page,
            Integer size,
            @Nullable List<String> sort
    ) {
        log.info("Getting all users REST controller...");

        var params = ParamsDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var paginatedUsers = userService.getAllUsers(params);

        log.info("Getting all users REST controller finished...");

        return ResponseEntity.ok(userRestMapper.toRestModelPaginatedUserResponseDTO(
                paginatedUsers));
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> getUserById(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {
        log.info("Getting user by id REST controller...");

        var params = ParamsDTO.builder()
                .id(id)
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var user = userService.getUserById(params);

        log.info("Getting user by id REST controller finished...");

        return ResponseEntity.ok(userRestMapper.toRestModelUserResponseDTO(user));
    }

    @Override
    public ResponseEntity<RestModelPaginatedUserResponseDTO> queryUsers(
            UUID correlationId,
            String consumerId,
            @Nullable RestModelUserQueryRequestDTO restModelUserQueryRequestDTO
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> restoreUser(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {
        log.info("Restoring user REST controller...");

        var params = ParamsDTO.builder()
                .id(id)
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var user = userService.restoreUser(params);

        log.info("Restoring user REST controller finished...");

        return ResponseEntity.ok(userRestMapper.toRestModelUserResponseDTO(user));
    }

    @Override
    public ResponseEntity<RestModelPaginatedUserResponseDTO> searchUsers(
            UUID correlationId,
            String consumerId,
            @Nullable RestModelUserSearchRequestDTO restModelUserSearchRequestDTO
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> updateUser(
            UUID correlationId, UUID id,
            String consumerId,
            RestModelUpdateUserRequestDTO restModelUpdateUserRequestDTO
    ) {
        log.info("Updating user REST controller...");

        var params = ParamsDTO.builder()
                .id(id)
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var user = userRestMapper.toUserDTO(restModelUpdateUserRequestDTO);

        var updatedUser = userService.updateUser(params, user);

        log.info("Updating user REST controller finished...");

        return ResponseEntity.ok(userRestMapper.toRestModelUserResponseDTO(updatedUser));
    }

}
