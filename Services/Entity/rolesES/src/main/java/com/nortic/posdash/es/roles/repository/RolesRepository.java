package com.nortic.posdash.es.roles.repository;

import com.nortic.posdash.models.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(String roleName);
    Page<RoleEntity> findAllByDeletedAtIsNull(Pageable pageable);
    List<RoleEntity> findAllByDeletedAtIsNull(Sort sort);
    Page<RoleEntity> findAllByDeletedAtIsNotNull(Pageable pageable);
}
