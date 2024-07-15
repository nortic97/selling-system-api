package com.nortic.posdash.es.roles.service;

import com.nortic.posdash.es.roles.repository.RolesRepository;
import com.nortic.posdash.es.roles.utils.CustomException;
import com.nortic.posdash.es.roles.utils.Mapper;
import com.nortic.posdash.models.dto.RoleDTO;
import com.nortic.posdash.models.entity.RoleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RolesServiceImpl implements IRolesService {

    private static final Logger log = LoggerFactory.getLogger(RolesServiceImpl.class);
    private final RolesRepository rolesRepository;
    private final Mapper mapper;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository, Mapper mapper) {
        this.rolesRepository = rolesRepository;
        this.mapper = mapper;
    }

    @Override
    public RoleDTO addRole(RoleDTO role) throws CustomException {
        log.info("Start addRole Service: {}", role.getName());

        // verify if role exists
        checkIfRoleExists(role.getName());

        // Convert DTO to Entity
        RoleEntity roleEntity = mapper.convertToEntity(role, RoleEntity.class);

        // Save Entity
        RoleEntity savedRole = rolesRepository.save(roleEntity);

        // Convert entity to DTO to return this.
        RoleDTO roleDTO = mapper.convertToDto(savedRole, RoleDTO.class);

        log.info("End addRole Service: {}", role.getName());

        return roleDTO;
    }

    @Override
    public RoleDTO getRoleById(UUID id) throws CustomException {
        log.info("Start getRoleById Service: {}", id);
        // Check if role exist by id.
        RoleEntity role = checkIfIdExists(id);
        log.info("End getRoleById Service: {}", id);
        return mapper.convertToDto(role, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> getAllRoles() throws CustomException {
        log.info("Start getAllRoles Service");
        List<RoleEntity> roles = rolesRepository.findAllByDeletedAtIsNull(Sort.by("name").ascending());
        log.info("End getAllRoles Service");
        return mapper.convertToDtoList(roles, RoleDTO.class);
    }

    @Override
    public Page<RoleDTO> getAllRoles(int page, int size) throws CustomException {
        log.info("Start getAllRoles Service");
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<RoleEntity> roles = rolesRepository.findAllByDeletedAtIsNull(pageable);
        log.info("End getAllRoles Service");
        return mapper.convertToDtoPage(roles, RoleDTO.class);
    }

    @Override
    public Page<RoleDTO> getAllDeletedRoles(int page, int size) throws CustomException {
        log.info("Start getAllDeletedRoles Service");
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<RoleEntity> roles = rolesRepository.findAllByDeletedAtIsNotNull(pageable);
        log.info("End getAllDeletedRoles Service");
        return mapper.convertToDtoPage(roles, RoleDTO.class);
    }

    @Override
    public RoleDTO updateRole(UUID id, RoleDTO roleDTO) throws CustomException {
        log.info("Start updateRole Service: {}", id);

        // Check if role exists by id.
        RoleEntity roleEntity = checkIfIdExists(id);

        roleEntity.setName(roleDTO.getName());
        roleEntity.setDescription(roleDTO.getDescription());
        roleEntity.setStatus(roleDTO.isStatus());
        roleEntity.setDeletedAt(roleDTO.getDeletedAt());

        RoleEntity updatedRole = rolesRepository.save(roleEntity);

        log.info("End updateRole Service: {}", id);

        return mapper.convertToDto(updatedRole, RoleDTO.class);
    }

    @Override
    public void deleteRoleById(UUID id) throws CustomException {
        log.info("Start deleteRoleById Service: {}", id);
        RoleEntity roleEntity = checkIfIdExists(id);
        roleEntity.setStatus(false);
        roleEntity.markAsDeleted();
        rolesRepository.save(roleEntity);
        log.info("End deleteRoleById Service: {}", id);
    }

    @Override
    public void permanentlyDeleteRoleById(UUID id) throws CustomException {
        log.info("Start permanentlyDeleteRoleById Service: {}", id);
        RoleEntity roleEntity = checkIfIdExists(id);
        rolesRepository.delete(roleEntity);
        log.info("End permanentlyDeleteRoleById Service: {}", id);
    }

    private void checkIfRoleExists(String roleName) throws CustomException {
        Optional<RoleEntity> role = rolesRepository.findByName(roleName);
        if (role.isPresent()) {
            throw new CustomException("Role already exists");
        }
    }

    private RoleEntity checkIfIdExists(UUID id) throws CustomException {
        Optional<RoleEntity> role = rolesRepository.findById(id);
        if (role.isEmpty()) {
            throw new CustomException("Role not found");
        }
        return role.get();
    }
}
