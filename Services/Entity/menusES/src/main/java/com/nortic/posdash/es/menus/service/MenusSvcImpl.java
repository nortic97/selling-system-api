package com.nortic.posdash.es.menus.service;

import com.nortic.posdash.es.menus.repository.MenusRepositoryImpl;
import com.nortic.posdash.es.menus.utils.CustomException;
import com.nortic.posdash.models.dto.MenuDTO;
import com.nortic.posdash.models.dto.SubMenuDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenusSvcImpl implements IMenusSvc {

    private static final Logger log = LoggerFactory.getLogger(MenusSvcImpl.class);

    private final MenusRepositoryImpl menuRepository;

    @Autowired
    public MenusSvcImpl(MenusRepositoryImpl menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuDTO> menusWithSubmenus() throws CustomException {
        log.info("Start menusWithSubmenus Service");
        log.info("End menusWithSubmenus Service");
        return mapResultsToMenu(menuRepository.menusWithSubmenus());
    }

    private List<MenuDTO> mapResultsToMenu(List<Object[]> results) {
        Map<UUID, MenuDTO> menuMap = new LinkedHashMap<>();

        for (Object[] result : results) {
            UUID menuId = (UUID) result[0];

            MenuDTO menu = menuMap.get(menuId);
            if (menu == null) {
                menu = new MenuDTO();
                menu.setId(menuId);
                menu.setName((String) result[1]);
                menu.setDescription((String) result[2]);
                menu.setHasSubmenu((Boolean) result[3]);
                menu.setIcon((String) result[4]);
                menu.setPosition((Integer) result[5]);
                menu.setStatus((Boolean) result[6]);
                menu.setUrl((String) result[7]);
                menu.setSubMenus(new ArrayList<>());
                menuMap.put(menuId, menu);
            }

            if (result[8] != null) {
                SubMenuDTO submenu = new SubMenuDTO();
                submenu.setId((UUID) result[8]);
                submenu.setName((String) result[9]);
                submenu.setDescription((String) result[10]);
                submenu.setPosition((Integer) result[11]);
                submenu.setStatus((Boolean) result[12]);
                submenu.setUrl((String) result[13]);
                submenu.setMenuId(menuId);
                menu.getSubMenus().add(submenu);
            }
        }
        return new ArrayList<>(menuMap.values());
    }
}
