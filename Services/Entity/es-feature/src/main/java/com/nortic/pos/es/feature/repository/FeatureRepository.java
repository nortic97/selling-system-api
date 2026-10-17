package com.nortic.pos.es.feature.repository;

import com.nortic.pos.es.feature.model.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, UUID> {

    @NativeQuery(
            """
                    SELECT * FROM feature WHERE id = :id AND deleted_at IS NOT NULL;
                    """
    )
    Optional<FeatureEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
