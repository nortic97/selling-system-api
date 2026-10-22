package com.nortic.pos.es.store.entity;

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
        name = "store",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_store_tax_id",
                        columnNames = "tax_id"
                ),
                @UniqueConstraint(
                        name = "uk_store_phone_number",
                        columnNames = "phone_number"
                ),
                @UniqueConstraint(
                        name = "uk_store_email",
                        columnNames = "email"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(
        sql = """
        UPDATE store
        SET deleted_at = CURRENT_TIMESTAMP,
            is_active = FALSE
        WHERE id = ?
        """
)
@SQLRestriction("deleted_at IS NULL")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private UUID id;

    @JoinColumn(
            name = "owner_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_store_owner_id")
    )
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity ownerId;

    @Column(
            name = "tax_id",
            length = 100,
            nullable = false
    )
    private String taxId;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "description",
            length = 1000
    )
    private String description;

    @Column(name = "slogan")
    private String slogan;

    @Column(
            name = "logo",
            length = 2000
    )
    private String logo;

    @Column(name = "address")
    private String address;

    @Column(
            name = "country",
            length = 100
    )
    private String country;

    @Column(
            name = "state_province",
            length = 100
    )
    private String stateProvince;

    @Column(
            name = "city",
            length = 100
    )
    private String city;

    @Column(
            name = "phone_number",
            length = 50
    )
    private String phoneNumber;

    @Column(name = "email")
    private String email;

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
