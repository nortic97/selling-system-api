package com.nortic.pos.es.user.service.contract;

import com.nortic.pos.es.user.dto.UserDTO;
import com.nortic.pos.es.user.model.entity.UserEntity;

import java.util.UUID;

public interface UserValidationService {

    void validatePasswordChange(
            String currentPassword,
            String newPassword,
            String encodedPassword
    );

    UserEntity validateAndGetUser(UUID id);

    void validateEmail(UUID id, String email);

    void validatePhone(UUID id, String phone);

    void validateNewUser(UserDTO userDTO);

    void validateUsername(UUID id, String username);

}
