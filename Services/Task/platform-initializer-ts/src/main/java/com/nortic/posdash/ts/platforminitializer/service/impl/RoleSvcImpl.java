package com.nortic.posdash.ts.platforminitializer.service.impl;

import com.nortic.posdash.models.entity.RoleEntity;
import com.nortic.posdash.ts.platforminitializer.repository.RoleRepository;
import com.nortic.posdash.ts.platforminitializer.service.contract.IRoleSvc;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleSvcImpl implements IRoleSvc {

    private static final Logger log = LoggerFactory.getLogger(RoleSvcImpl.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleSvcImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void insertRoleIfNotExist(String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            RoleEntity role = new RoleEntity();
            role.setName(roleName);
            role.setDescription("Platform role created");
            role.setStatus(true);
            roleRepository.save(role);
            log.info("{} role added", roleName);
        }
    }

    @Override
    public RoleEntity getRoleByRoleName(String roleName) {
        return roleRepository.findByName(roleName).orElse(new RoleEntity());
    }
}
