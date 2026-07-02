package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.exception.UserBusinessException;
import com.nortic.pos.es.user.exception.UserExceptionCode;
import com.nortic.pos.es.user.model.entity.UserEntity;
import com.nortic.pos.es.user.repository.UserRepository;
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

}