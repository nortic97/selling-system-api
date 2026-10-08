package com.nortic.pos.es.permission.service;

import com.nortic.pos.es.permission.exception.BusinessException;
import com.nortic.pos.es.permission.exception.ExceptionCode;
import com.nortic.pos.es.permission.mapper.PermissionEntityMapper;
import com.nortic.pos.es.permission.model.dto.*;
import com.nortic.pos.es.permission.repository.PermissionRepository;
import com.nortic.pos.es.permission.service.contract.PermissionService;
import com.nortic.pos.es.permission.service.contract.PermissionValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final PermissionEntityMapper permissionEntityMapper;

    private final PermissionValidationService permissionValidationService;

    @Override
    public PermissionDTO getPermission(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Get permission by id {} service ...", id);

        var permission = permissionValidationService.validateAndGetPermission(id);

        log.info("Get permission by id {} service completed ...", id);

        return permissionEntityMapper.toPermissionDTO(permission);
    }

    @Override
    public PermissionDTO createPermission(RequestContextDTO requestContextDTO, PermissionDTO permissionDTO) {
        log.info("Create permission service ...");

        permissionValidationService.validateNewPermission(permissionDTO);

        var permissionEntity = permissionEntityMapper.toPermissionEntity(permissionDTO);
        permissionEntity.setIsActive(permissionDTO.getIsActive() != null ? permissionDTO.getIsActive() : Boolean.TRUE);

        var savedPermissionEntity = permissionRepository.save(permissionEntity);

        log.info("Create permission service completed ...");

        return permissionEntityMapper.toPermissionDTO(savedPermissionEntity);
    }

    @Override
    public PaginatedPermissionResponseDTO listPermissions(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("List permissions service ...");

        var permissions = permissionRepository.findAll(searchParamsDTO.getPageable());

        log.info("List permissions service completed ... ");

        return permissionEntityMapper.toListPermissionDTO(permissions);
    }

    @Override
    @Transactional
    public void deletePermission(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Delete permission service... ");

        var permissionEntity = permissionValidationService.validateAndGetPermission(id);

        permissionRepository.delete(permissionEntity);

        log.info("Delete permission service completed ... ");
    }

    @Override
    @Transactional
    public PermissionDTO restorePermission(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restore permission service ... ");

        var permission = permissionRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new BusinessException(ExceptionCode.PERMISSION_NOT_FOUND));

        permission.setIsActive(Boolean.TRUE);
        permission.setDeletedAt(null);

        log.info("Restore permission service completed ... ");
        return permissionEntityMapper.toPermissionDTO(permission);
    }

    @Override
    @Transactional
    public PermissionDTO updatePermission(RequestContextDTO requestContextDTO, UUID id, PermissionDTO permissionDTO) {
        log.info("Update permission service ... ");

        var permissionEntity = permissionValidationService.validateAndGetPermission(id);

        permissionValidationService.validateCode(permissionDTO.getCode(), id);

        permissionEntityMapper.updatePermissionFromDTO(permissionDTO, permissionEntity);

        log.info("Update permission service completed ... ");

        return permissionEntityMapper.toPermissionDTO(permissionEntity);
    }

    @Override
    @Transactional
    public PermissionDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeStatusRequestDTO changeStatusRequestDTO) {
        log.info("Change status service ... ");

        var permissionEntity = permissionValidationService.validateAndGetPermission(id);

        permissionEntity.setIsActive(changeStatusRequestDTO.getActive());

        log.info("Change status service completed ... ");

        return permissionEntityMapper.toPermissionDTO(permissionEntity);
    }

}
