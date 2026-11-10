package com.nortic.pos.es.employeeworkspace.repository;

import com.nortic.pos.es.employeeworkspace.model.entity.EmployeeWorkspaceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeWorkspaceRepository extends JpaRepository<EmployeeWorkspaceEntity, UUID>, JpaSpecificationExecutor<EmployeeWorkspaceEntity> {

    Page<EmployeeWorkspaceEntity> findByStoreIdIdAndIsActiveTrue(UUID storeId, Pageable pageable);

    Optional<EmployeeWorkspaceEntity> findByIdAndIsActiveTrue(UUID id);

    Optional<EmployeeWorkspaceEntity> findByEmployeeIdIdAndStoreIdIdAndIsDefaultTrueAndIsActiveTrue(UUID employeeId, UUID storeId);

    boolean existsByEmployeeIdIdAndStoreIdIdAndBranchIdIdAndRoleIdIdAndIsActiveTrue(UUID employeeId, UUID storeId, UUID branchId, UUID roleId);

    boolean existsByEmployeeIdIdAndStoreIdIdAndIsDefaultTrueAndIsActiveTrueAndIdNot(UUID employeeId, UUID storeId, UUID id);

    @NativeQuery("""
            SELECT * FROM "employee_workspace" WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
            """)
    Optional<EmployeeWorkspaceEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
