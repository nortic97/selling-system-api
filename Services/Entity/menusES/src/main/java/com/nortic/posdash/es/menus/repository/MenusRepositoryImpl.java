package com.nortic.posdash.es.menus.repository;

import com.nortic.posdash.es.menus.utils.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenusRepositoryImpl implements IMenusRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Object[]> menusWithSubmenus() throws CustomException {
        Query query = em.createNativeQuery("SELECT * FROM menus_with_submenus");
        return query.getResultList();
    }
}
