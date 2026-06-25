package com.nortic.pos.es.user.controller;

import com.nortic.pos.es.user.mapper.UserRestMapper;
import com.nortic.pos.es.user.rest.gen.api.UserApi;
import com.nortic.pos.es.user.rest.gen.model.RestModelCreateUserRequestDTO;
import com.nortic.pos.es.user.rest.gen.model.RestModelPaginatedUserResponseDTO;
import com.nortic.pos.es.user.rest.gen.model.RestModelUpdateUserRequestDTO;
import com.nortic.pos.es.user.rest.gen.model.RestModelUserResponseDTO;
import com.nortic.pos.es.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserApi {

    private final UserRestMapper userRestMapper;
    private final IUserService userService;

    @Override
    public ResponseEntity<RestModelUserResponseDTO> createUser(
            UUID correlationId,
            String consumerId,
            RestModelCreateUserRequestDTO restModelCreateUserRequestDTO
    ) {
        log.info("Creating user REST controller...");

        var user = userRestMapper.toUserDTO(restModelCreateUserRequestDTO);

        var createdUser = userService.createUser(user);

        log.info("Creating user REST Controller finished...");

        return ResponseEntity.ok()
                             .body(userRestMapper.toRestModelUserResponseDTO(
                                     createdUser));

    }

    @Override
    public ResponseEntity<Void> deleteUser(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPaginatedUserResponseDTO> getAllUsers(
            UUID correlationId,
            Integer page,
            Integer size,
            List<String> sort
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> getUserById(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelUserResponseDTO> restoreUser(
            UUID correlationId,
            UUID id,
            String consumerId
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
        return null;
    }


}
