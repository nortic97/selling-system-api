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
@Table(name = "store")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE store SET deleted_at = CURRENT_TIMESTAMP, is_active = FALSE WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "owner_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity ownerId;

    @Column(name = "tax_id", nullable = false, length = 100, unique = true)
    private String taxId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "slogan", length = 255)
    private String slogan;

    @Column(name = "logo", length = 2000)
    private String logo;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "state_province", length = 100)
    private String stateProvince;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "phone_number", length = 50, unique = true)
    private String phoneNumber;

    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime deletedAt;

}
