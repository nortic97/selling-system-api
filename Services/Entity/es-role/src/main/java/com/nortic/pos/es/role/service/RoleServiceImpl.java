package com.nortic.pos.es.role.service;

import com.nortic.pos.es.role.mapper.RoleEntityMapper;
import com.nortic.pos.es.role.model.dto.*;
import com.nortic.pos.es.role.repository.RoleRepository;
import com.nortic.pos.es.role.service.contract.RoleService;
import com.nortic.pos.es.role.service.contract.RoleValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final RoleValidationService roleValidationService;

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
                new RuntimeException("Role not found"));

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
}
