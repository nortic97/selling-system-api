package com.nortic.pos.es.role.service;

import com.nortic.pos.es.role.repository.RoleRepository;
import com.nortic.pos.es.role.util.CustomException;
import com.nortic.pos.es.role.util.ServiceMapper;
import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleSvcImpl implements IRoleSvc {

    private final RoleRepository roleRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        log.info("Create role service Started...");
        RoleEntity role = serviceMapper.dtoToEntity(roleDTO);
        log.info("Role entity created: {}", role.getId());
        log.info("Create role service Finished...");
        return serviceMapper.entityToDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO getRole(UUID id) {
        log.info("Get role service Started...");
        log.info("Get role service Finished...");
        return serviceMapper.entityToDTO(roleRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found")));
    }

    @Override
    public RoleDTO updateRole(UUID id, RoleDTO roleDTO) {
        log.info("Update role service Started...");

         var role = roleRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());

        var roleUpdated = serviceMapper.entityToDTO(roleRepository.save(role));

        log.info("Role updated: {}", roleUpdated.getId());
        log.info("Update role service Finished...");

        return roleUpdated;
    }

    @Override
    public void deleteRole(UUID id) {
        log.info("Delete role service Started...");
        roleRepository.deleteById(id);
        log.info("Role deleted: {}", id);
        log.info("Delete role service Finished...");
    }

    @Override
    public void softDeleteRole(UUID id) {
        log.info("Soft delete role service Started...");
        var role = roleRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        role.markAsDeleted();

        roleRepository.save(role);
        log.info("Role soft deleted: {}", role.getId());
        log.info("Soft delete role service Finished...");
    }

    @Override
    public void changeRoleStatus(UUID id, Boolean status) {
        log.info("Change role status service Started...");
        var role = roleRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        role.changeStatus(status);

        roleRepository.save(role);

        log.info("Role status changed: {}", role.getId());
        log.info("Change role status service Finished...");
    }

    @Override
    public Page<RoleDTO> getRoles(Pageable pageable, UUID storeId, String name, Boolean isActive) {
        return null;
    }

    @Override
    public Page<RoleDTO> getDeletedRoles(Pageable pageable, UUID storeId, String name) {
        return null;
    }
}
