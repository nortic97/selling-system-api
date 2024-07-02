package com.nortic.posdash.ms.usersettings.service;

import com.nortic.posdash.models.dto.MenuByRoleDTO;
import com.nortic.posdash.models.dto.SubMenuByRoleDTO;
import com.nortic.posdash.ms.usersettings.repository.FunctionsSqlRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserSettingsService {

    private final FunctionsSqlRepositoryImpl functionsSqlRepository;

    @Autowired
    public UserSettingsService(FunctionsSqlRepositoryImpl functionsSqlRepository) {
        this.functionsSqlRepository = functionsSqlRepository;
    }

    public List<MenuByRoleDTO> getMenusByRoleId(UUID roleId) {
        return mapResultsToMenuDTO(functionsSqlRepository.getMenuByRole(roleId));
    }

    private List<MenuByRoleDTO> mapResultsToMenuDTO(List<Object[]> results) {
        Map<UUID, MenuByRoleDTO> menuMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            UUID menuId = (UUID) result[0];

            MenuByRoleDTO menuDTO = menuMap.get(menuId);
            if (menuDTO == null) {
                menuDTO = new MenuByRoleDTO();
                menuDTO.setMenuId(menuId);
                menuDTO.setMenuName((String) result[1]);
                menuDTO.setMenuIcon((String) result[2]);
                menuDTO.setMenuPosition((Integer) result[3]);
                menuDTO.setHasSubmenu((Boolean) result[4]);
                menuDTO.setMenuUrl((String) result[5]);
                menuDTO.setSubMenus(new ArrayList<>());
                menuMap.put(menuId, menuDTO);
            }

            if (result[5] != null) {
                SubMenuByRoleDTO subMenuDTO = new SubMenuByRoleDTO();
                subMenuDTO.setSubmenuId((UUID) result[6]);
                subMenuDTO.setSubmenuName((String) result[7]);
                subMenuDTO.setSubmenuPosition((Integer) result[8]);
                subMenuDTO.setSubmenuUrl((String) result[9]);
                menuDTO.getSubMenus().add(subMenuDTO);
            }
        }

        return new ArrayList<>(menuMap.values());
    }

}
