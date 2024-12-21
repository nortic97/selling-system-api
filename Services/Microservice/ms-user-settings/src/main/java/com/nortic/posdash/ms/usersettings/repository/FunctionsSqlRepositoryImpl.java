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
    public List<Object[]> getMenuByRole(UUID roleId) {
        Query query = em.createNativeQuery("SELECT * FROM get_menu_by_role(:roleId)");
        query.setParameter("roleId", roleId);
        return query.getResultList();
    }
}
