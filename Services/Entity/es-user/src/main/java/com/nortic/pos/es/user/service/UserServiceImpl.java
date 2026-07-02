package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.dto.SearchParamsDTO;
import com.nortic.pos.es.user.dto.request.*;
import com.nortic.pos.es.user.dto.response.PaginatedUserResponseDTO;
import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import com.nortic.pos.es.user.mapper.UserEntityMapper;
import com.nortic.pos.es.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserEntityMapper userEntityMapper;

    private final UserValidationService userValidationService;

    @Override
    public UserDTO changeEmail(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangeEmailRequestDTO changeEmailRequestDTO
    ) {
        log.info("Changing email service...");

        var user = userValidationService.validateAndGetUser(id);

        userValidationService.validateEmail(id, changeEmailRequestDTO.getEmail());

        user.setEmail(changeEmailRequestDTO.getEmail().trim().toLowerCase());

        var savedUser = userRepository.save(user);

        log.info("Changing email service complete.");

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO changePhone(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangePhoneRequestDTO changePhoneRequestDTO
    ) {
        log.info("Changing phone service...");

        var user = userValidationService.validateAndGetUser(id);

        userValidationService.validatePhone(id, changePhoneRequestDTO.getPhone());

        user.setPhone(changePhoneRequestDTO.getPhone());

        var savedUser = userRepository.save(user);

        log.info("Changing phone service complete.");

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO changeUserPassword(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangePasswordRequestDTO changePasswordRequestDTO
    ) {
        log.info("Changing password service...");

        var user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserBusinessException(
                                UserExceptionCode.USER_NOT_FOUND
                        )
                );

        userValidationService.validatePasswordChange(
                changePasswordRequestDTO.getCurrentPassword(),
                changePasswordRequestDTO.getNewPassword(),
                user.getPassword()
        );

        user.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));

        var savedUser = userRepository.save(user);

        log.info("Changing password service complete.");

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO changeUserStatus(RequestContextDTO requestContextDTO, UUID id, ChangeUserStatusRequestDTO changeStatusRequestDTO) {
        return null;
    }

    @Override
    public UserDTO changeUsername(RequestContextDTO requestContextDTO, UUID id, ChangeUsernameRequestDTO changeUsernameRequestDTO) {
        return null;
    }

    @Override
    public UserDTO createUser(RequestContextDTO requestContextDTO, UserDTO userDTO) {
        log.info("Creating user service...");

        validateUser(userDTO);

        var user = userEntityMapper.toUserEntity(userDTO);

        user.setIsActive(Boolean.FALSE);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        var savedUser = userRepository.save(user);

        log.info("User created whit id {}", savedUser.getId());

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    public void deleteUser(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Deleting user service...");

        userRepository.delete(userRepository.findById(id).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND)));

        log.info("User deleted whit id {}", id);
    }

    @Override
    public PaginatedUserResponseDTO getAllUsers(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("Getting user service...");

        var users = userRepository.findAll(searchParamsDTO.getPageable());

        log.info("Getting user service complete.");

        return userEntityMapper.toPaginatedUserDTO(users);
    }

    @Override
    public UserDTO getUserById(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Getting user by id service...");

        var user = userRepository.findById(id).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        log.info("Getting user by id service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    public PaginatedUserResponseDTO queryUsers(RequestContextDTO requestContextDTO, UserQueryRequestDTO userQueryRequestDTO) {
        return null;
    }

    @Override
    public UserDTO restoreUser(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restoring user service...");

        var user = userRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        user.setIsActive(Boolean.TRUE);
        user.setDeletedAt(null);

        var savedUser = userRepository.save(user);

        log.info("Restoring user service complete.");

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    public PaginatedUserResponseDTO searchUsers(RequestContextDTO requestContextDTO, UserSearchRequestDTO userSearchRequestDTO) {
        return null;
    }

    @Override
    public UserDTO updateUser(RequestContextDTO requestContextDTO, UUID id, UserDTO userDTO) {
        log.info("Updating user service...");

        var user = userRepository.findById(id).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        userEntityMapper.updateUserFromDTO(userDTO, user);

        var savedUser = userRepository.save(user);

        log.info("Updating user service complete.");

        return userEntityMapper.toUserDTO(savedUser);
    }

    private void validateUser(UserDTO userDTO) {

        if (userRepository.existsByUsernameIgnoreCase(userDTO.getUsername())) {
            log.error("User {} already exists", userDTO.getUsername());
            throw new UserBusinessException(UserExceptionCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmailIgnoreCase(userDTO.getEmail())) {
            log.error("User {} already exists", userDTO.getEmail());
            throw new UserBusinessException(UserExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByDocumentTypeIgnoreCaseAndDocumentNumber(userDTO.getDocumentType(), userDTO.getDocumentNumber())) {
            log.error("User {} already exists", userDTO.getDocumentNumber());
            throw new UserBusinessException(UserExceptionCode.DOCUMENT_ALREADY_EXISTS);
        }
    }
}
