package com.nortic.pos.es.role.repository;

import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID>, JpaSpecificationExecutor<RoleEntity> {
    Optional<RoleEntity> findByNameIgnoreCaseAndStore(String name, StoreEntity store);
    List<RoleEntity> findAllByDeletedAtNotNull();
    Page<RoleEntity> findAllByDeletedAtNotNull(Pageable pageable);
}
