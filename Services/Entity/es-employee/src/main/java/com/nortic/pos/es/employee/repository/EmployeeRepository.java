package com.nortic.pos.es.employee.repository;

import com.nortic.pos.es.employee.model.entity.EmployeeEntity;
import com.nortic.pos.es.employee.model.enums.DocumentTypeEnum;
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

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByPhoneIgnoreCaseAndIdNot(String phone, UUID id);

    boolean existsByPhoneAndIdNot(String phone, UUID id);

    boolean existsByDocumentTypeAndDocumentNumber(
            DocumentTypeEnum documentType,
            String documentNumber
    );

    boolean existsByEmailIgnoreCaseAndIdNot(
            String email,
            UUID id
    );

    boolean existsByUsernameIgnoreCaseAndIdNot(String username, UUID id);

    boolean existsByStoreIdAndUsernameIgnoreCase(UUID storeId, String username);

    boolean existsByStoreIdAndEmailIgnoreCase(UUID storeId, String email);

    boolean existsByStoreIdAndPhoneIgnoreCase(UUID storeId, String phone);

    boolean existsByStoreIdAndDocumentTypeAndDocumentNumber(
            UUID storeId,
            DocumentTypeEnum documentType,
            String documentNumber
    );

    boolean existsByStoreIdAndUsernameIgnoreCaseAndIdNot(UUID storeId, String username, UUID id);

    boolean existsByStoreIdAndEmailIgnoreCaseAndIdNot(UUID storeId, String email, UUID id);

    boolean existsByStoreIdAndPhoneIgnoreCaseAndIdNot(UUID storeId, String phone, UUID id);

    List<EmployeeEntity> id(UUID id);

    @NativeQuery(
            """
                    SELECT * FROM "employee" WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<EmployeeEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
