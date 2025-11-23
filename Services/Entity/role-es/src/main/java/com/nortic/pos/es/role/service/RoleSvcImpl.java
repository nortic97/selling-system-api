package com.nortic.pos.es.role.service;

import com.nortic.pos.es.role.repository.RoleRepository;
import com.nortic.pos.es.role.util.CustomException;
import com.nortic.pos.es.role.util.ServiceMapper;
import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleSvcImpl implements IRoleSvc {

    private final RoleRepository roleRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        log.info("Create role Service Start...");
        validateRoleName(
                roleDTO.getName(),
                roleDTO.getStore()
                       .getId()
        );
        var role = serviceMapper.dtoToEntity(roleDTO);
        log.info(
                "Role entity created: {}",
                role.getId()
        );
        log.info("Create role Service End...");
        return serviceMapper.entityToDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO getRoleById(UUID id) {
        log.info("Get role Service Start...");
        log.info(
                "Retrieving role with id {}",
                id
        );
        log.info("Get role Service End...");
        return serviceMapper.entityToDTO(getRoleEntity(id));
    }

    @Override
    public RoleDTO updateRole(
            UUID id,
            RoleDTO roleDTO
    ) {
        log.info("Update role Service Start...");
        log.info(
                "Updating role with id {}",
                id
        );

        var role = getRoleEntity(id);

        validateRoleName(
                roleDTO.getName(),
                roleDTO.getStore()
                       .getId(),
                id
        );

        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());

        var roleUpdated = serviceMapper.entityToDTO(roleRepository.save(role));

        log.info(
                "Role updated: {}",
                roleUpdated.getId()
        );
        log.info("Update role Service End...");

        return roleUpdated;
    }

    @Override
    public void deleteRole(UUID id) {
        log.info("Delete role Service Start...");
        log.info(
                "Deleting role with id {}",
                id
        );

        var role = getRoleEntity(id);
        roleRepository.deleteById(role.getId());

        log.info(
                "Role deleted: {}",
                role.getId()
        );
        log.info("Delete role Service End...");
    }

    @Override
    public void softDeleteRole(UUID id) {
        log.info("Soft delete role Service Start...");

        var role = getRoleEntity(id);

        if (role.getDeletedAt() != null) {
            log.error(
                    "Role with ID: {} is already deleted",
                    id
            );
            throw new CustomException(
                    409,
                    "Role with ID: " + id + " is already deleted"
            );
        }

        role.markAsDeleted();

        roleRepository.save(role);
        log.info(
                "Role soft deleted: {}",
                role.getId()
        );
        log.info("Soft delete role Service End...");
    }

    @Override
    public void changeRoleStatus(
            UUID id,
            Boolean status
    ) {
        log.info("Change role status Service Start...");

        var role = getRoleEntity(id);

        if (Objects.equals(
                role.getIsActive(),
                status
        )) {
            log.warn(
                    "Role with id {} already has status {}",
                    id,
                    status
            );
            throw new CustomException(
                    409,
                    "Role with id " + id + " already has status " + status
            );
        }

        role.changeStatus(status);

        roleRepository.save(role);

        log.info(
                "Role status changed: {}",
                role.getId()
        );
        log.info("Change role status Service End...");
    }

    private RoleEntity getRoleEntity(UUID id) {
        return roleRepository.findById(id)
                             .orElseThrow(() -> new CustomException(
                                     404,
                                     "ID: " + id + " Not Found"
                             ));
    }

    private void validateRoleName(
            String name,
            UUID storeId,
            UUID currentRoleId
    ) {
        var roleEntity = roleRepository.findByNameIgnoreCaseAndStore(
                name,
                StoreEntity
                        .builder()
                        .id(storeId)
                        .build()
        );

        if (roleEntity.isPresent() && (currentRoleId == null || !roleEntity.get()
                                                                           .getId()
                                                                           .equals(currentRoleId))) {
            log.warn(
                    "Role with name {} already exists",
                    name
            );
            throw new CustomException(
                    409,
                    "Role with name " + name + " already exists"
            );
        }
    }

    private void validateRoleName(
            String name,
            UUID storeId
    ) {
        validateRoleName(
                name,
                storeId,
                null
        );
    }
}
