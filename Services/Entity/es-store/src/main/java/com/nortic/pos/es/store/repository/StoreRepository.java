package com.nortic.pos.es.store.repository;

import com.nortic.pos.es.store.model.entity.StoreEntity;
import com.nortic.pos.es.store.model.enums.TaxTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {

    boolean existsByTaxTypeAndTaxIdIgnoreCase(TaxTypeEnum taxType, String taxId);

    boolean existsByTaxTypeAndTaxIdIgnoreCaseAndIdNot(TaxTypeEnum taxType, String taxId, UUID id);

    boolean existsByContactEmailIgnoreCase(String contactEmail);

    boolean existsByContactEmailIgnoreCaseAndIdNot(String contactEmail, UUID id);

    @NativeQuery(
            """
                    SELECT * FROM store WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<StoreEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
