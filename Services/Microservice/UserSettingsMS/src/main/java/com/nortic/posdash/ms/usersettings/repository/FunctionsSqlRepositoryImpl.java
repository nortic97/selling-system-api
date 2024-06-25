package com.nortic.posdash.ms.usersettings.repository;

import com.nortic.posdash.models.dto.MenuByRoleDTO;
import com.nortic.posdash.models.dto.SubMenuByRoleDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FunctionsSqlRepositoryImpl implements IFunctionsSqlRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MenuByRoleDTO> getMenuByRole(UUID roleId) {
        Query query = em.createNativeQuery("SELECT * FROM get_menu_by_role(:roleId)");
        query.setParameter("roleId", roleId);

        List<Object[]> results = query.getResultList();
        return mapResultsToMenuDTO(results);
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
                menuDTO.setMenuUrl((String) result[4]);
                menuDTO.setSubMenus(new ArrayList<>());
                menuMap.put(menuId, menuDTO);
            }

            if (result[5] != null) {
                SubMenuByRoleDTO subMenuDTO = new SubMenuByRoleDTO();
                subMenuDTO.setSubmenuId((UUID) result[5]);
                subMenuDTO.setSubmenuName((String) result[6]);
                subMenuDTO.setSubmenuPosition((Integer) result[7]);
                subMenuDTO.setSubmenuUrl((String) result[8]);
                menuDTO.getSubMenus().add(subMenuDTO);
            }
        }

        return new ArrayList<>(menuMap.values());
    }
}
