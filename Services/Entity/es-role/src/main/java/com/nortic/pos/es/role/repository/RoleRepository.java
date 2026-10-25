package com.nortic.pos.es.role.repository;

import com.nortic.pos.es.role.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    boolean existsByStoreIdIdAndNameIgnoreCase(UUID storeId, String name);

    boolean existsByStoreIdIdAndNameIgnoreCaseAndIdNot(UUID storeId, String name, UUID id);

    @NativeQuery(
            """
                    SELECT * FROM role WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<RoleEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
