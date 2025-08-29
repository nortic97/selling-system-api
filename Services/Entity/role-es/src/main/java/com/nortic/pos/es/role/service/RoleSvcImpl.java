package com.nortic.pos.es.role.service;

import com.nortic.pos.es.role.repository.RoleRepository;
import com.nortic.pos.es.role.util.CustomException;
import com.nortic.pos.es.role.util.RestMapper;
import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RoleSvcImpl implements IRoleSvc {

    private final RoleRepository roleRepository;
    private final RestMapper restMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        log.info("Create role service");
        RoleEntity role = restMapper.dtoToEntity(roleDTO);
        return restMapper.entityToDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO getRole(UUID id) {
        return null;
    }

    @Override
    public RoleDTO updateRole(UUID id, RoleDTO roleDTO) {
        return null;
    }

    @Override
    public void deleteRole(UUID id) {

    }

    @Override
    public void softDeleteRole(UUID id) {

    }

    @Override
    public void changeRoleStatus(UUID id, Boolean status) {

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
