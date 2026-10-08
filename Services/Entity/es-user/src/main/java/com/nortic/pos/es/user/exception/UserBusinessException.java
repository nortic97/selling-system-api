package com.nortic.pos.es.user.exception;

import com.nortic.pos.es.user.dto.response.FieldErrorDetailResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class UserBusinessException extends RuntimeException {

    private final UserExceptionCode userExceptionCode;

    private final List<FieldErrorDetailResponseDTO> details;

    public UserBusinessException(
            UserExceptionCode userExceptionCode
    ) {
        super(userExceptionCode.getMessage());

        this.userExceptionCode = userExceptionCode;
        this.details = null;
    }

    public UserBusinessException(
            UserExceptionCode userExceptionCode,
            String message
    ) {
        super(message);

        this.userExceptionCode = userExceptionCode;
        this.details = null;
    }

}