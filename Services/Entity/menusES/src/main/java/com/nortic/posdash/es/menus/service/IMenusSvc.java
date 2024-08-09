package com.nortic.posdash.es.menus.service;

import com.nortic.posdash.es.menus.utils.CustomException;
import com.nortic.posdash.models.dto.MenuDTO;

import java.util.List;

public interface IMenusSvc {
    /**
     * Get all menus but use menu by role because is same model.
     * @return All menus with yours submenus.
     */
    List<MenuDTO> menusWithSubmenus() throws CustomException;
}
