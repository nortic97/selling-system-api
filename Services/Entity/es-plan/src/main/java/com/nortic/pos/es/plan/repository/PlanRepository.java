package com.nortic.pos.es.plan.repository;

import com.nortic.pos.es.plan.model.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, UUID> {

    @NativeQuery(
            """
                    SELECT * FROM plan WHERE id = :id AND deleted_at IS NOT NULL;
                    """
    )
    Optional<PlanEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
