package com.nortic.pos.es.user.exception;

import com.nortic.pos.es.user.dto.FieldErrorDetailDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class UserBusinessException extends RuntimeException {

    private final UserExceptionCode userExceptionCode;

    private final List<FieldErrorDetailDTO> details;

    public UserBusinessException(
            UserExceptionCode userExceptionCode
    ) {
        super(userExceptionCode.getMessage());

        this.userExceptionCode = userExceptionCode;
        this.details = null;
    }

    public UserBusinessException(
            UserExceptionCode userExceptionCode,
            List<FieldErrorDetailDTO> details
    ) {
        super(userExceptionCode.getMessage());

        this.userExceptionCode = userExceptionCode;
        this.details = details;
    }
}