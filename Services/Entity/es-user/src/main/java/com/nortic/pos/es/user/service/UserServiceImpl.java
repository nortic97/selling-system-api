package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import com.nortic.pos.es.user.mapper.UserEntityMapper;
import com.nortic.pos.es.user.model.dto.*;
import com.nortic.pos.es.user.repository.UserRepository;
import com.nortic.pos.es.user.service.contract.UserService;
import com.nortic.pos.es.user.service.contract.UserSpecificationService;
import com.nortic.pos.es.user.service.contract.UserValidationService;
import com.nortic.pos.es.user.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserEntityMapper userEntityMapper;

    private final UserValidationService userValidationService;

    private final UserSpecificationService userSpecificationService;

    @Override
    @Transactional
    public UserDTO changeEmail(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangeEmailRequestDTO changeEmailRequestDTO
    ) {
        log.info("Changing email service...");

        var user = userValidationService.validateAndGetUser(id);

        userValidationService.validateEmail(id, changeEmailRequestDTO.getEmail());

        user.setEmail(changeEmailRequestDTO.getEmail().trim().toLowerCase());

        log.info("Changing email service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO changePhone(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangePhoneRequestDTO changePhoneRequestDTO
    ) {
        log.info("Changing phone service...");

        var user = userValidationService.validateAndGetUser(id);

        userValidationService.validatePhone(id, changePhoneRequestDTO.getPhone());

        user.setPhone(changePhoneRequestDTO.getPhone());

        log.info("Changing phone service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO changeUserPassword(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangePasswordRequestDTO changePasswordRequestDTO
    ) {
        log.info("Changing password service...");

        var user = userValidationService.validateAndGetUser(id);

        userValidationService.validatePasswordChange(
                changePasswordRequestDTO.getCurrentPassword(),
                changePasswordRequestDTO.getNewPassword(),
                user.getPassword()
        );

        user.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));

        log.info("Changing password service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO changeUserStatus(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangeUserStatusRequestDTO changeStatusRequestDTO
    ) {
        log.info("Changing user status service...");

        var user = userValidationService.validateAndGetUser(id);

        user.setIsActive(changeStatusRequestDTO.getActive());

        log.info("Changing user status service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO changeUsername(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangeUsernameRequestDTO changeUsernameRequestDTO
    ) {
        log.info("Changing username service...");

        var user = userValidationService.validateAndGetUser(id);

        userValidationService.validateUsername(id, changeUsernameRequestDTO.getUsername());

        user.setUsername(changeUsernameRequestDTO.getUsername().trim().toLowerCase());

        log.info("Changing username service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    public UserDTO authenticate(
            RequestContextDTO requestContextDTO,
            AuthenticateRequestDTO authenticateRequestDTO
    ) {
        log.info("Authenticating user service...");

        String normalizedUsername = authenticateRequestDTO.getUsername().trim().toLowerCase();

        var user = userRepository.findByUsernameIgnoreCase(normalizedUsername)
                .orElseThrow(() -> new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(authenticateRequestDTO.getPassword(), user.getPassword())) {
            throw new UserBusinessException(UserExceptionCode.INVALID_PASSWORD);
        }

        log.info("Authentication service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    public UserDTO createUser(RequestContextDTO requestContextDTO, UserDTO userDTO) {
        log.info("Creating user service...");

        userValidationService.validateNewUser(userDTO);

        var user = userEntityMapper.toUserEntity(userDTO);

        user.setIsActive(Boolean.FALSE);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        var savedUser = userRepository.save(user);

        log.info("User created whit id {}", savedUser.getId());

        return userEntityMapper.toUserDTO(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Deleting user service...");

        var user = userValidationService.validateAndGetUser(id);
        userRepository.delete(user);

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

        var user = userValidationService.validateAndGetUser(id);

        log.info("Getting user by id service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    public PaginatedUserResponseDTO queryUsers(RequestContextDTO requestContextDTO, UserQueryRequestDTO userQueryRequestDTO) {
        log.info("Querying users service...");

        var pageable = PageableUtil.buildPageable(
                userQueryRequestDTO.getPage(),
                userQueryRequestDTO.getSize(),
                userQueryRequestDTO.getSort()
        );

        var specification = userSpecificationService.buildQuerySpecification(userQueryRequestDTO);

        var users = userRepository.findAll(specification, pageable);

        log.info("Querying users service complete.");

        return userEntityMapper.toPaginatedUserDTO(users);
    }

    @Override
    @Transactional
    public UserDTO restoreUser(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restoring user service...");

        var user = userRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new UserBusinessException(UserExceptionCode.USER_NOT_FOUND));

        user.setIsActive(Boolean.TRUE);
        user.setDeletedAt(null);

        log.info("Restoring user service complete.");

        return userEntityMapper.toUserDTO(user);
    }

    @Override
    public PaginatedUserResponseDTO searchUsers(RequestContextDTO requestContextDTO, UserSearchRequestDTO userSearchRequestDTO) {
        log.info("Searching users service...");

        var pageable = PageableUtil.buildPageable(
                userSearchRequestDTO.getPage(),
                userSearchRequestDTO.getSize(),
                userSearchRequestDTO.getSort()
        );

        var specification = userSpecificationService.buildSearchSpecification(userSearchRequestDTO);

        var users = userRepository.findAll(specification, pageable);

        log.info("Searching users service complete.");

        return userEntityMapper.toPaginatedUserDTO(users);
    }

    @Override
    @Transactional
    public UserDTO updateUser(
            RequestContextDTO requestContextDTO,
            UUID id,
            UserDTO userDTO
    ) {
        log.info("Updating user service...");

        var user = userValidationService.validateAndGetUser(id);

        userEntityMapper.updateUserFromDTO(userDTO, user);

        log.info("Updating user service complete.");

        return userEntityMapper.toUserDTO(user);
    }

}
