package com.nortic.pos.es.user.service;

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
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        log.info("Creating user service...");

        validateUser(userDTO);

        var user = userEntityMapper.toUserEntity(userDTO);

        user.setIsActive(Boolean.FALSE);

        var savedUser = userRepository.save(user);

        log.info(
                "User created whit id {}",
                savedUser.getId()
        );

        return userEntityMapper.toUserDTO(savedUser);
    }

    private void validateUser(UserDTO userDTO) {

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            log.error(
                    "User {} already exists",
                    userDTO.getUsername()
            );
            throw new UserBusinessException(UserExceptionCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            log.error(
                    "User {} already exists",
                    userDTO.getEmail()
            );
            throw new UserBusinessException(UserExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        if (
                userRepository.existsByDocumentTypeAndDocumentNumber(
                        userDTO.getDocumentType(),
                        userDTO.getDocumentNumber()
                )
        ) {
            log.error(
                    "User {} already exists",
                    userDTO.getDocumentNumber()
            );
            throw new UserBusinessException(UserExceptionCode.DOCUMENT_ALREADY_EXISTS);
        }
    }
}
