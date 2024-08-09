package com.nortic.posdash.es.menus.repository;

import com.nortic.posdash.es.menus.utils.CustomException;

import java.util.List;

public interface IMenusRepository {
    /**
     * Get All menus
     *
     * @return a list object with information.
     */
    List<Object[]> menusWithSubmenus() throws CustomException;
}
