package com.nortic.pos.es.store.exception;

import com.nortic.pos.es.store.dto.error.FieldErrorDetailResponseDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
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
            String logMessage,
            Object... args
    ) {
        super(exceptionCode.getMessage());

        this.exceptionCode = exceptionCode;
        this.details = null;

        log.error(logMessage, args);
    }

}