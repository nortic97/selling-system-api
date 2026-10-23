package com.nortic.pos.es.branch.repository;

import com.nortic.pos.es.branch.model.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {

    boolean existsByStoreIdIdAndNameIgnoreCase(UUID storeId, String name);

    boolean existsByStoreIdIdAndNameIgnoreCaseAndIdNot(UUID storeId, String name, UUID id);

    @NativeQuery(
            """
                    SELECT * FROM branch WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<BranchEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
