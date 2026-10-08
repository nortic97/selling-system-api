package com.nortic.pos.es.permission.repository;

import com.nortic.pos.es.permission.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

    boolean existsByCode(String code);

    @NativeQuery(
            """
                    SELECT * FROM "permission" WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<PermissionEntity> findByIdIncludingDeleted(@Param("id") UUID id);

    boolean existsByCodeAndIdNot(String code, UUID id);

}
