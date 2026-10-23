package com.nortic.pos.es.branch.exception;

import com.nortic.pos.es.branch.model.dto.FieldErrorDetailResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class BusinessException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    private final List<FieldErrorDetailResponseDTO> details;

    public BusinessException(
            ExceptionCode exceptionCode
    ) {
        super(exceptionCode.getMessage());

        this.exceptionCode = exceptionCode;
        this.details = null;
    }

    public BusinessException(
            ExceptionCode exceptionCode,
            List<FieldErrorDetailResponseDTO> details
    ) {
        super(exceptionCode.getMessage());

        this.exceptionCode = exceptionCode;
        this.details = details;
    }

}
