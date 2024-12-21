package com.nortic.posdash.ts.platforminitializer.repository;

import com.nortic.posdash.models.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    boolean existsByName(String name);
    MenuEntity findByName(String name);
}
