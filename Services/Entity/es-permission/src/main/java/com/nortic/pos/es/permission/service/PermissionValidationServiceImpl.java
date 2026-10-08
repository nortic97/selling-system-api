package com.nortic.pos.es.permission.service;

import com.nortic.pos.es.permission.dto.response.PermissionDTO;
import com.nortic.pos.es.permission.exception.BusinessException;
import com.nortic.pos.es.permission.exception.ExceptionCode;
import com.nortic.pos.es.permission.model.entity.PermissionEntity;
import com.nortic.pos.es.permission.repository.PermissionRepository;
import com.nortic.pos.es.permission.service.contract.PermissionValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
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

        if (permissionRepository.existsByCode(permissionDTO.getCode())) {
            throw new BusinessException(ExceptionCode.CODE_ALREADY_EXISTS, "Permission code {} already exists", permissionDTO.getCode());
        }

    }

    @Override
    public void validateCode(String code, UUID id) {
        if (permissionRepository.existsByCodeAndIdNot(code, id)) {
            throw new BusinessException(ExceptionCode.CODE_ALREADY_EXISTS, "Permission code {} already exists", code);
        }
    }

}
