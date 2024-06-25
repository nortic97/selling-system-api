package com.nortic.posdash.ms.usersettings.repository;

import com.nortic.posdash.models.dto.MenuByRoleDTO;

import java.util.List;
import java.util.UUID;

public interface IFunctionsSqlRepository {
    List<MenuByRoleDTO> getMenuByRole(UUID roleId);
}
