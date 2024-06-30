package com.nortic.posdash.ts.platforminitializer.repository;

import com.nortic.posdash.models.entity.SubMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubMenuRepository extends JpaRepository<SubMenuEntity, UUID> {
    boolean existsByName(String name);
}
