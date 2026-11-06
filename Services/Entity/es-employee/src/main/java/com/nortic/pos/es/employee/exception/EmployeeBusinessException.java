package com.nortic.pos.es.employee.exception;

import com.nortic.pos.es.employee.model.dto.FieldErrorDetailResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class EmployeeBusinessException extends RuntimeException {

    private final EmployeeExceptionCode employeeExceptionCode;

    private final List<FieldErrorDetailResponseDTO> details;

    public EmployeeBusinessException(
            EmployeeExceptionCode employeeExceptionCode
    ) {
        super(employeeExceptionCode.getMessage());

        this.employeeExceptionCode = employeeExceptionCode;
        this.details = null;
    }

    public EmployeeBusinessException(
            EmployeeExceptionCode employeeExceptionCode,
            List<FieldErrorDetailResponseDTO> details
    ) {
        super(employeeExceptionCode.getMessage());

        this.employeeExceptionCode = employeeExceptionCode;
        this.details = details;
    }

}