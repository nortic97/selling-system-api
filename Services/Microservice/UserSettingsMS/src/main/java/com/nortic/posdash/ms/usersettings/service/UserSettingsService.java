package com.nortic.posdash.ms.usersettings.service;

import com.nortic.posdash.models.dto.MenuByRoleDTO;
import com.nortic.posdash.ms.usersettings.repository.IFunctionsSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserSettingsService {

    private final IFunctionsSqlRepository functionsSqlRepository;

    @Autowired
    public UserSettingsService(IFunctionsSqlRepository functionsSqlRepository) {
        this.functionsSqlRepository = functionsSqlRepository;
    }

    public List<MenuByRoleDTO> getMenusByRoleId(UUID roleId) {
        return functionsSqlRepository.getMenuByRole(roleId);
    }

}
