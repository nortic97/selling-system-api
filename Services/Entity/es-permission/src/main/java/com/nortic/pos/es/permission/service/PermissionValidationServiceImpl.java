package com.nortic.pos.es.permission.service;

import com.nortic.pos.es.permission.exception.BusinessException;
import com.nortic.pos.es.permission.exception.ExceptionCode;
import com.nortic.pos.es.permission.model.dto.PermissionDTO;
import com.nortic.pos.es.permission.model.entity.PermissionEntity;
import com.nortic.pos.es.permission.repository.PermissionRepository;
import com.nortic.pos.es.permission.service.contract.PermissionValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionValidationServiceImpl implements PermissionValidationService {

    private final PermissionRepository permissionRepository;

    @Override
    public PermissionEntity validateAndGetPermission(UUID id) {
        return permissionRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.PERMISSION_NOT_FOUND));
    }

    @Override
    public void validateNewPermission(PermissionDTO permissionDTO) {

        if (permissionDTO.getCode() == null || permissionDTO.getCode().isBlank()) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }

        if (permissionDTO.getModule() == null || permissionDTO.getModule().isBlank()) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }

        if (permissionDTO.getName() == null || permissionDTO.getName().isBlank()) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }

        if (permissionRepository.existsByCode(permissionDTO.getCode())) {
            throw new BusinessException(ExceptionCode.CODE_ALREADY_EXISTS);
        }

    }

    @Override
    public void validateCode(String code, UUID id) {
        if (code == null || code.isBlank()) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }

        if (permissionRepository.existsByCodeAndIdNot(code, id)) {
            throw new BusinessException(ExceptionCode.CODE_ALREADY_EXISTS);
        }
    }

}
