package com.nortic.pos.es.employee.repository;

import com.nortic.pos.es.employee.model.entity.EmployeeEntity;
import com.nortic.pos.es.employee.model.enums.DocumentTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID>, JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findByUsernameIgnoreCase(String username);

    Page<EmployeeEntity> findByStoreIdId(UUID storeId, Pageable pageable);

    boolean existsByStoreIdIdAndUsernameIgnoreCase(UUID storeId, String username);

    boolean existsByStoreIdIdAndEmailIgnoreCase(UUID storeId, String email);

    boolean existsByStoreIdIdAndPhoneIgnoreCase(UUID storeId, String phone);

    boolean existsByStoreIdIdAndDocumentTypeAndDocumentNumber(UUID storeId, DocumentTypeEnum documentType, String documentNumber);

    boolean existsByStoreIdIdAndUsernameIgnoreCaseAndIdNot(UUID storeId, String username, UUID id);

    boolean existsByStoreIdIdAndEmailIgnoreCaseAndIdNot(UUID storeId, String email, UUID id);

    boolean existsByStoreIdIdAndPhoneIgnoreCaseAndIdNot(UUID storeId, String phone, UUID id);

    List<EmployeeEntity> id(UUID id);

    @NativeQuery("""
            SELECT * FROM "employee" WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
            """)
    Optional<EmployeeEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
