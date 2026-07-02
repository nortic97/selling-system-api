package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.dto.PaginatedUserDTO;
import com.nortic.pos.es.user.dto.ParamsDTO;
import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import com.nortic.pos.es.user.mapper.UserEntityMapper;
import com.nortic.pos.es.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO, ParamsDTO paramsDTO) {
        log.info("Creating user service...");

        validateUser(userDTO);

        var user = userEntityMapper.toUserEntity(userDTO);

        user.setIsActive(Boolean.FALSE);

        var savedUser = userRepository.save(user);

        log.info("User created whit id {}", savedUser.getId());

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    public void deleteUser(ParamsDTO paramsDTO) {
        log.info("Deleting user service...");

        userRepository.delete(userRepository.findById(paramsDTO.getId()).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND)));

        log.info("User deleted whit id {}", paramsDTO.getId());
    }

    @Override
    public PaginatedUserDTO getAllUsers(ParamsDTO paramsDTO) {
        log.info("Getting user service...");

        var users = userRepository.findAll(paramsDTO.getPageable());

        log.info("Getting user service complete.");

        return userEntityMapper.toPaginatedUserDTO(users);
    }

    @Override
    public UserDTO getUserById(ParamsDTO paramsDTO) {
        log.info("Getting user by id service...");

        var user = userRepository.findById(paramsDTO.getId()).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        log.info("Getting user by id service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    public UserDTO restoreUser(ParamsDTO paramsDTO) {
        log.info("Restoring user service...");

        var user = userRepository.findByIdIncludingDeleted(paramsDTO.getId()).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        user.setIsActive(Boolean.TRUE);
        user.setDeletedAt(null);

        var savedUser = userRepository.save(user);

        log.info("Restoring user service complete.");

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(ParamsDTO paramsDTO, UserDTO userDTO) {
        log.info("Updating user service...");

        var user = userRepository.findById(paramsDTO.getId()).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        userEntityMapper.updateUserFromDTO(userDTO, user);

        var savedUser = userRepository.save(user);

        log.info("Updating user service complete.");

        return userEntityMapper.toUserDTO(savedUser);
    }

    private void validateUser(UserDTO userDTO) {

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            log.error("User {} already exists", userDTO.getUsername());
            throw new UserBusinessException(UserExceptionCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            log.error("User {} already exists", userDTO.getEmail());
            throw new UserBusinessException(UserExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByDocumentTypeAndDocumentNumber(userDTO.getDocumentType(), userDTO.getDocumentNumber())) {
            log.error("User {} already exists", userDTO.getDocumentNumber());
            throw new UserBusinessException(UserExceptionCode.DOCUMENT_ALREADY_EXISTS);
        }
    }
}
