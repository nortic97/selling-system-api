package com.nortic.pos.es.role.service;

import com.nortic.pos.es.role.repository.RoleRepository;
import com.nortic.pos.es.role.util.RestMapper;
import com.nortic.pos.models.dto.RoleDTO;
import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.es.role.util.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class RoleSvcImpl implements IRoleSvc {

    private final RoleRepository roleRepository;
    private final RestMapper restMapper;

    @Autowired
    RoleSvcImpl(RoleRepository roleRepository, RestMapper restMapper) {
        this.roleRepository = roleRepository;
        this.restMapper = restMapper;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        log.info("Create role service");
        RoleEntity role = restMapper.dtoToEntity(roleDTO);
        return restMapper.entityToDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO getRoleById(UUID id) {
        log.info("Get role bi id: {}", id);
        return roleRepository.findById(id)
                .map(restMapper::entityToDTO)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));
    }
}
