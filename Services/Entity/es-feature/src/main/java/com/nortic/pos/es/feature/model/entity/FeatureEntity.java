package com.nortic.pos.es.feature.model.entity;

import com.nortic.pos.es.feature.model.enums.FeatureTypeEnum;
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
@Table(
        name = "feature",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_feature_code",
                        columnNames = "code"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(
        sql = """
        UPDATE feature
        SET deleted_at = CURRENT_TIMESTAMP
        WHERE id = ?
        """
)
@SQLRestriction("deleted_at IS NULL")
public class FeatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private UUID id;

    @Column(
            name = "code",
            length = 100,
            nullable = false
    )
    private String code;

    @Column(
            name = "name",
            length = 150,
            nullable = false
    )
    private String name;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            length = 50,
            nullable = false
    )
    private FeatureTypeEnum type;

    @Column(
            name = "is_active",
            nullable = false
    )
    private Boolean isActive;

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
