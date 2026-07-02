package com.nortic.pos.es.user.controller;

import com.nortic.pos.es.user.dto.SearchParamsDTO;
import com.nortic.pos.es.user.dto.request.RequestContextDTO;
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
    public ResponseEntity<RestModelUserResponseDTO> changeEmail(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangeEmailRequest restModelChangeEmailRequest
    ) {
        log.info("Changing email Controller complete.");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeEmailRequestDTO = userRestMapper.toChangeEmailRequestDTO(restModelChangeEmailRequest);

        var changedUser = userService.changeEmail(requestContextDTO, id, changeEmailRequestDTO);

        log.info("Changing email Controller complete.");

        return ResponseEntity.ok(userRestMapper.toRestModelUserResponseDTO(changedUser));
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> changePhone(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangePhoneRequest restModelChangePhoneRequest
    ) {
        log.info("Changing phone Controller complete.");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changePhoneRequestDTO = userRestMapper.toChangePhoneRequestDTO(restModelChangePhoneRequest);

        var changedUser = userService.changePhone(requestContextDTO, id, changePhoneRequestDTO);

        log.info("Changing phone Controller complete.");

        return ResponseEntity.ok(userRestMapper.toRestModelUserResponseDTO(changedUser));
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> changeUserPassword(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangePasswordRequest restModelChangePasswordRequest
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> changeUserStatus(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangeUserStatusRequest restModelChangeUserStatusRequest
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> changeUsername(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangeUsernameRequest restModelChangeUsernameRequest
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> createUser(
            UUID correlationId,
            String consumerId,
            RestModelCreateUserRequestDTO restModelCreateUserRequestDTO
    ) {
        log.info("Creating user REST controller...");

        var user = userRestMapper.toUserDTO(restModelCreateUserRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdUser = userService.createUser(requestContextDTO, user);

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

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        userService.deleteUser(requestContextDTO, id);

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

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var paginatedUsers = userService.getAllUsers(requestContextDTO, pagination);

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

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var user = userService.getUserById(requestContextDTO, id);

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

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var user = userService.restoreUser(requestContextDTO, id);

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
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelUpdateUserRequestDTO restModelUpdateUserRequestDTO
    ) {
        log.info("Updating user REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var user = userRestMapper.toUserDTO(restModelUpdateUserRequestDTO);

        var updatedUser = userService.updateUser(requestContextDTO, id, user);

        log.info("Updating user REST controller finished...");

        return ResponseEntity.ok(userRestMapper.toRestModelUserResponseDTO(updatedUser));
    }

}
