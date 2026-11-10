package com.nortic.pos.es.employeeworkspace.exception;

import com.nortic.pos.es.employeeworkspace.model.dto.FieldErrorDetailResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class EmployeeWorkspaceBusinessException extends RuntimeException {

    private final EmployeeWorkspaceExceptionCode employeeWorkspaceExceptionCode;

    private final List<FieldErrorDetailResponseDTO> details;

    public EmployeeWorkspaceBusinessException(
            EmployeeWorkspaceExceptionCode employeeWorkspaceExceptionCode
    ) {
        super(employeeWorkspaceExceptionCode.getMessage());

        this.employeeWorkspaceExceptionCode = employeeWorkspaceExceptionCode;
        this.details = null;
    }

    public EmployeeWorkspaceBusinessException(
            EmployeeWorkspaceExceptionCode employeeWorkspaceExceptionCode,
            List<FieldErrorDetailResponseDTO> details
    ) {
        super(employeeWorkspaceExceptionCode.getMessage());

        this.employeeWorkspaceExceptionCode = employeeWorkspaceExceptionCode;
        this.details = details;
    }

}
