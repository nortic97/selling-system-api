package com.nortic.pos.es.employeeworkspace.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "employee_workspace")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = """
        UPDATE "employee_workspace"
        SET
            updated_at = CURRENT_TIMESTAMP,
            deleted_at = CURRENT_TIMESTAMP,
            is_active = FALSE
        WHERE id = ?
        """)
@SQLRestriction("deleted_at IS NULL")
public class EmployeeWorkspaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "fk_employee_workspace_store"))
    private StoreEntity storeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "fk_employee_workspace_employee"))
    private EmployeeEntity employeeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id", nullable = false, foreignKey = @ForeignKey(name = "fk_employee_workspace_branch"))
    private BranchEntity branchId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_employee_workspace_role"))
    private RoleEntity roleId;

    @Builder.Default
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = Boolean.FALSE;

    @Column(name = "last_access_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime lastAccessAt;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime deletedAt;
}
