package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import com.nortic.pos.es.user.entity.UserEntity;
import com.nortic.pos.es.user.repository.UserRepository;
import com.nortic.pos.es.user.service.contract.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void validatePasswordChange(String currentPassword, String newPassword, String encodedPassword) {
        if (!passwordEncoder.matches(
                currentPassword,
                encodedPassword
        )) {

            throw new UserBusinessException(
                    UserExceptionCode.INVALID_PASSWORD
            );
        }

        if (passwordEncoder.matches(
                newPassword,
                encodedPassword
        )) {

            throw new UserBusinessException(
                    UserExceptionCode.PASSWORD_ALREADY_IN_USE
            );
        }
    }

    @Override
    public UserEntity validateAndGetUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserBusinessException(
                                UserExceptionCode.USER_NOT_FOUND
                        )
                );
    }

    @Override
    public void validateEmail(UUID id, String email) {
        if (userRepository.existsByEmailIgnoreCaseAndIdNot(
                email,
                id
        )) {

            throw new UserBusinessException(
                    UserExceptionCode.EMAIL_ALREADY_EXISTS
            );
        }
    }

    @Override
    public void validatePhone(UUID id, String phone) {
        if (userRepository.existsByPhoneAndIdNot(
                phone,
                id
        )) {

            throw new UserBusinessException(
                    UserExceptionCode.PHONE_ALREADY_EXISTS
            );
        }
    }

    @Override
    public void validateNewUser(UserDTO userDTO) {
        if (userRepository.existsByUsernameIgnoreCase(userDTO.getUsername())) {
            log.error("User {} already exists", userDTO.getUsername());
            throw new UserBusinessException(UserExceptionCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmailIgnoreCase(userDTO.getEmail())) {
            log.error("User {} already exists", userDTO.getEmail());
            throw new UserBusinessException(UserExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByDocumentTypeAndDocumentNumber(
                userDTO.getDocumentType(),
                userDTO.getDocumentNumber()
        )) {
            log.error("User {} already exists", userDTO.getDocumentNumber());
            throw new UserBusinessException(UserExceptionCode.DOCUMENT_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateUsername(UUID id, String username) {
        if (userRepository.existsByUsernameIgnoreCaseAndIdNot(username, id)) {
            log.error("Username {} already exists", username);
            throw new UserBusinessException(UserExceptionCode.USERNAME_ALREADY_EXISTS);
        }
    }

}