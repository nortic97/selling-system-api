package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import com.nortic.pos.es.user.model.dto.UserDTO;
import com.nortic.pos.es.user.model.entity.UserEntity;
import com.nortic.pos.es.user.repository.UserRepository;
import com.nortic.pos.es.user.service.contract.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        String normalizedEmail = email.trim().toLowerCase();
        if (userRepository.existsByEmailIgnoreCaseAndIdNot(
                normalizedEmail,
                id
        )) {

            throw new UserBusinessException(
                    UserExceptionCode.EMAIL_ALREADY_EXISTS
            );
        }
    }

    @Override
    public void validatePhone(UUID id, String phone) {
        String normalizedPhone = phone.trim().toLowerCase();
        if (userRepository.existsByPhoneIgnoreCaseAndIdNot(
                normalizedPhone,
                id
        )) {

            throw new UserBusinessException(
                    UserExceptionCode.PHONE_ALREADY_EXISTS
            );
        }
    }

    @Override
    public void validateNewUser(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isBlank()) {
            throw new UserBusinessException(UserExceptionCode.VALIDATION_FAILED);
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new UserBusinessException(UserExceptionCode.VALIDATION_FAILED);
        }
        if (userDTO.getDocumentNumber() == null || userDTO.getDocumentNumber().isBlank()) {
            throw new UserBusinessException(UserExceptionCode.VALIDATION_FAILED);
        }
        if (userDTO.getDocumentType() == null) {
            throw new UserBusinessException(UserExceptionCode.VALIDATION_FAILED);
        }
        if (userDTO.getFirstName() == null || userDTO.getFirstName().isBlank()) {
            throw new UserBusinessException(UserExceptionCode.VALIDATION_FAILED);
        }
        if (userDTO.getLastName() == null || userDTO.getLastName().isBlank()) {
            throw new UserBusinessException(UserExceptionCode.VALIDATION_FAILED);
        }
        if (userDTO.getPhone() == null || userDTO.getPhone().isBlank()) {
            throw new UserBusinessException(UserExceptionCode.VALIDATION_FAILED);
        }

        String normalizedUsername = userDTO.getUsername().trim().toLowerCase();
        String normalizedEmail = userDTO.getEmail().trim().toLowerCase();
        String normalizedPhone = userDTO.getPhone().trim().toLowerCase();

        if (userRepository.existsByUsernameIgnoreCase(normalizedUsername)) {
            throw new UserBusinessException(UserExceptionCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new UserBusinessException(UserExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByPhoneIgnoreCase(normalizedPhone)) {
            throw new UserBusinessException(UserExceptionCode.PHONE_ALREADY_EXISTS);
        }

        if (userRepository.existsByDocumentTypeAndDocumentNumber(
                userDTO.getDocumentType(),
                userDTO.getDocumentNumber()
        )) {
            throw new UserBusinessException(UserExceptionCode.DOCUMENT_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateUsername(UUID id, String username) {
        String normalizedUsername = username.trim().toLowerCase();
        if (userRepository.existsByUsernameIgnoreCaseAndIdNot(normalizedUsername, id)) {
            throw new UserBusinessException(UserExceptionCode.USERNAME_ALREADY_EXISTS);
        }
    }

}