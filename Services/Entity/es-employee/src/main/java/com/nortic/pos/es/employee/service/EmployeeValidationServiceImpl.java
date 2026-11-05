package com.nortic.pos.es.employee.service;

import com.nortic.pos.es.employee.exception.EmployeeBusinessException;
import com.nortic.pos.es.employee.exception.EmployeeExceptionCode;
import com.nortic.pos.es.employee.model.dto.EmployeeDTO;
import com.nortic.pos.es.employee.model.dto.FieldErrorDetailResponseDTO;
import com.nortic.pos.es.employee.model.entity.EmployeeEntity;
import com.nortic.pos.es.employee.model.enums.ValidationFieldEnum;
import com.nortic.pos.es.employee.repository.EmployeeRepository;
import com.nortic.pos.es.employee.service.contract.EmployeeValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeValidationServiceImpl implements EmployeeValidationService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void validatePasswordChange(String currentPassword, String newPassword, String encodedPassword) {
        if (!passwordEncoder.matches(
                currentPassword,
                encodedPassword
        )) {

            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.INVALID_PASSWORD
            );
        }

        if (passwordEncoder.matches(
                newPassword,
                encodedPassword
        )) {

            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.PASSWORD_ALREADY_IN_USE
            );
        }
    }

    @Override
    public EmployeeEntity validateAndGetEmployee(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeBusinessException(
                                EmployeeExceptionCode.EMPLOYEE_NOT_FOUND
                        )
                );
    }

    @Override
    public void validateEmail(UUID id, String email) {
        String normalizedEmail = email.trim().toLowerCase();
        var employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeBusinessException(EmployeeExceptionCode.EMPLOYEE_NOT_FOUND));
        
        if (employeeRepository.existsByStoreIdAndEmailIgnoreCaseAndIdNot(
                employee.getStoreId(), normalizedEmail, id)) {

            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.EMAIL_ALREADY_EXISTS
            );
        }
    }

    @Override
    public void validatePhone(UUID id, String phone) {
        String normalizedPhone = phone.trim().toLowerCase();
        var employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeBusinessException(EmployeeExceptionCode.EMPLOYEE_NOT_FOUND));
        
        if (employeeRepository.existsByStoreIdAndPhoneIgnoreCaseAndIdNot(
                employee.getStoreId(), normalizedPhone, id)) {

            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.PHONE_ALREADY_EXISTS
            );
        }
    }

    @Override
    public void validateNewEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getUsername() == null || employeeDTO.getUsername().isBlank()) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.USERNAME.getField())
                            .message(ValidationFieldEnum.USERNAME.getMessage())
                            .build())
            );
        }
        if (employeeDTO.getEmail() == null || employeeDTO.getEmail().isBlank()) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.EMAIL.getField())
                            .message(ValidationFieldEnum.EMAIL.getMessage())
                            .build())
            );
        }
        if (employeeDTO.getDocumentNumber() == null || employeeDTO.getDocumentNumber().isBlank()) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.DOCUMENT_NUMBER.getField())
                            .message(ValidationFieldEnum.DOCUMENT_NUMBER.getMessage())
                            .build())
            );
        }
        if (employeeDTO.getDocumentType() == null) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.DOCUMENT_TYPE.getField())
                            .message(ValidationFieldEnum.DOCUMENT_TYPE.getMessage())
                            .build())
            );
        }
        if (employeeDTO.getFirstName() == null || employeeDTO.getFirstName().isBlank()) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.FIRST_NAME.getField())
                            .message(ValidationFieldEnum.FIRST_NAME.getMessage())
                            .build())
            );
        }
        if (employeeDTO.getLastName() == null || employeeDTO.getLastName().isBlank()) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.LAST_NAME.getField())
                            .message(ValidationFieldEnum.LAST_NAME.getMessage())
                            .build())
            );
        }
        if (employeeDTO.getPhone() == null || employeeDTO.getPhone().isBlank()) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.PHONE.getField())
                            .message(ValidationFieldEnum.PHONE.getMessage())
                            .build())
            );
        }

        if (employeeDTO.getStoreId() == null) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.STORE_ID.getField())
                            .message(ValidationFieldEnum.STORE_ID.getMessage())
                            .build())
            );
        }

        String normalizedUsername = employeeDTO.getUsername().trim().toLowerCase();
        String normalizedEmail = employeeDTO.getEmail().trim().toLowerCase();
        String normalizedPhone = employeeDTO.getPhone().trim().toLowerCase();

        if (employeeRepository.existsByStoreIdAndUsernameIgnoreCase(employeeDTO.getStoreId(), normalizedUsername)) {
            throw new EmployeeBusinessException(EmployeeExceptionCode.USERNAME_ALREADY_EXISTS);
        }

        if (employeeRepository.existsByStoreIdAndEmailIgnoreCase(employeeDTO.getStoreId(), normalizedEmail)) {
            throw new EmployeeBusinessException(EmployeeExceptionCode.EMAIL_ALREADY_EXISTS);
        }

        if (employeeRepository.existsByStoreIdAndPhoneIgnoreCase(employeeDTO.getStoreId(), normalizedPhone)) {
            throw new EmployeeBusinessException(EmployeeExceptionCode.PHONE_ALREADY_EXISTS);
        }

        if (employeeRepository.existsByStoreIdAndDocumentTypeAndDocumentNumber(
                employeeDTO.getStoreId(),
                employeeDTO.getDocumentType(),
                employeeDTO.getDocumentNumber()
        )) {
            throw new EmployeeBusinessException(EmployeeExceptionCode.DOCUMENT_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateUsername(UUID id, String username) {
        String normalizedUsername = username.trim().toLowerCase();
        var employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeBusinessException(EmployeeExceptionCode.EMPLOYEE_NOT_FOUND));
        
        if (employeeRepository.existsByStoreIdAndUsernameIgnoreCaseAndIdNot(
                employee.getStoreId(), normalizedUsername, id)) {
            throw new EmployeeBusinessException(EmployeeExceptionCode.USERNAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateStoreId(UUID storeId) {
        if (storeId == null) {
            throw new EmployeeBusinessException(
                    EmployeeExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.STORE_ID.getField())
                            .message(ValidationFieldEnum.STORE_ID.getMessage())
                            .build())
            );
        }
    }

}