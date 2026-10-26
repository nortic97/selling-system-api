package com.nortic.pos.es.role.service;

import com.nortic.pos.es.role.exception.BusinessException;
import com.nortic.pos.es.role.exception.ExceptionCode;
import com.nortic.pos.es.role.mapper.PermissionMapper;
import com.nortic.pos.es.role.mapper.RoleEntityMapper;
import com.nortic.pos.es.role.model.dto.*;
import com.nortic.pos.es.role.repository.RoleRepository;
import com.nortic.pos.es.role.service.contract.RoleService;
import com.nortic.pos.es.role.service.contract.RoleValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final RoleValidationService roleValidationService;
    private final PermissionMapper permissionMapper;

    @Override
    public RoleDTO getRole(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Get role by id {} service ...", id);

        var role = roleValidationService.validateAndGetRole(id);

        log.info("Get role by id {} service completed ...", id);

        return roleEntityMapper.toRoleDTO(role);
    }

    @Override
    public RoleDTO createRole(RequestContextDTO requestContextDTO, RoleDTO roleDTO) {
        log.info("Create role service ...");

        roleValidationService.validateNewRole(roleDTO);

        var roleEntity = roleEntityMapper.toRoleEntity(roleDTO);
        roleEntity.setIsActive(Boolean.TRUE);

        var savedRoleEntity = roleRepository.save(roleEntity);

        log.info("Create role service completed ...");

        return roleEntityMapper.toRoleDTO(savedRoleEntity);
    }

    @Override
    public PaginatedRoleResponseDTO listRoles(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("List roles service ...");

        var roles = roleRepository.findAll(searchParamsDTO.getPageable());

        log.info("List roles service completed ... ");

        return roleEntityMapper.toListRoleDTO(roles);
    }

    @Override
    @Transactional
    public void deleteRole(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Delete role service... ");

        var roleEntity = roleValidationService.validateAndGetRole(id);

        roleRepository.delete(roleEntity);

        log.info("Delete role service completed ... ");
    }

    @Override
    @Transactional
    public RoleDTO restoreRole(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restore role service ... ");

        var role = roleRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new BusinessException(ExceptionCode.ROLE_NOT_FOUND));

        role.setIsActive(Boolean.TRUE);
        role.setDeletedAt(null);

        log.info("Restore role service completed ... ");
        return roleEntityMapper.toRoleDTO(role);
    }

    @Override
    @Transactional
    public RoleDTO updateRole(RequestContextDTO requestContextDTO, UUID id, RoleDTO roleDTO) {
        log.info("Update role service ... ");

        var roleEntity = roleValidationService.validateAndGetRole(id);

        roleValidationService.validateRoleUpdate(id, roleDTO);

        roleEntityMapper.updateRoleFromDTO(roleDTO, roleEntity);

        log.info("Update role service completed ... ");

        return roleEntityMapper.toRoleDTO(roleEntity);
    }

    @Override
    @Transactional
    public RoleDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeStatusRequestDTO changeStatusRequestDTO) {
        log.info("Change status service ... ");

        var roleEntity = roleValidationService.validateAndGetRole(id);

        roleEntity.setIsActive(changeStatusRequestDTO.getActive());

        log.info("Change status service completed ... ");

        return roleEntityMapper.toRoleDTO(roleEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponseDTO> getRolePermission(RequestContextDTO requestContextDTO, UUID roleId) {
        log.info("Get role permission service ...");

        var role = roleValidationService.validateAndGetRole(roleId);

        var permissions = role.getPermissions().stream().toList();

        log.info("Get role permission service completed ...");

        return permissionMapper.toPermissionResponseDTOList(permissions);
    }

    @Override
    @Transactional
    public List<PermissionResponseDTO> addPermissionToRole(RequestContextDTO requestContextDTO, UUID roleId, PermissionRequestDTO permissionRequestDTO) {
        log.info("Add permission to role service ...");

        var role = roleValidationService.validateAndGetRole(roleId);

        if (permissionRequestDTO == null || permissionRequestDTO.getPermissionIds() == null || permissionRequestDTO.getPermissionIds().isEmpty()) {
            return Collections.emptyList();
        }

        var uniquePermissionIds = permissionRequestDTO.getPermissionIds().stream().distinct().toList();
        var permissionEntities = roleRepository.findPermissionsByIds(uniquePermissionIds);

        if (permissionEntities.size() != uniquePermissionIds.size()) {
            throw new BusinessException(ExceptionCode.PERMISSION_NOT_FOUND);
        }

        role.getPermissions().addAll(permissionEntities);

        roleRepository.save(role);

        log.info("Add permission to role service completed ...");

        return permissionMapper.toPermissionResponseDTOList(permissionEntities);
    }

    @Override
    @Transactional
    public void removePermissionsFromRole(RequestContextDTO requestContextDTO, UUID roleId, PermissionRequestDTO permissionRequestDTO) {
        log.info("Remove permissions from role service ...");

        var role = roleValidationService.validateAndGetRole(roleId);

        if (permissionRequestDTO != null && permissionRequestDTO.getPermissionIds() != null && !permissionRequestDTO.getPermissionIds().isEmpty()) {
            role.getPermissions().removeIf(permission -> permissionRequestDTO.getPermissionIds().contains(permission.getId()));
            roleRepository.save(role);
        }

        log.info("Remove permissions from role service completed ...");
    }

    @Override
    @Transactional
    public void removePermissionFromRole(RequestContextDTO requestContextDTO, UUID roleId, UUID permissionId) {
        log.info("Remove permission from role service ...");

        var role = roleValidationService.validateAndGetRole(roleId);

        boolean removed = role.getPermissions().removeIf(permission -> permission.getId().equals(permissionId));

        if (!removed) {
            throw new BusinessException(ExceptionCode.PERMISSION_NOT_FOUND);
        }

        roleRepository.save(role);

        log.info("Remove permission from role service completed ...");
    }
}
