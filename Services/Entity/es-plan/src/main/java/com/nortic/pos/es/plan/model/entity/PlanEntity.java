package com.nortic.pos.es.plan.model.entity;

import com.nortic.pos.es.plan.model.enums.PlanTypeOfPlanEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "plan")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(
        sql = """
        UPDATE plan
        SET deleted_at = CURRENT_TIMESTAMP
        WHERE id = ?
        """
)
@SQLRestriction("deleted_at IS NULL")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private UUID id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "price",
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type_of_plan",
            length = 100,
            nullable = false
    )
    private PlanTypeOfPlanEnum typeOfPlan;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(
            name = "updated_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime updatedAt;

    @Column(
            name = "deleted_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime deletedAt;
}
