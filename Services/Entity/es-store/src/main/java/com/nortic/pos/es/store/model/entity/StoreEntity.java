package com.nortic.pos.es.store.model.entity;

import com.nortic.pos.es.store.model.enums.CurrencyCodeEnum;
import com.nortic.pos.es.store.model.enums.TaxTypeEnum;
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
@SQLDelete(sql = """
        UPDATE store
           SET deleted_at = CURRENT_TIMESTAMP,
               is_active = FALSE,
               updated_at = CURRENT_TIMESTAMP
         WHERE id = ?
        """)
@SQLRestriction("deleted_at IS NULL")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false, foreignKey = @ForeignKey(name = "fk_store_owner"))
    private UserEntity ownerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", length = 50, nullable = false)
    private TaxTypeEnum taxType;

    @Column(name = "tax_id", length = 100, nullable = false)
    private String taxId;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "address")
    private String address;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "state_province", length = 100)
    private String stateProvince;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code", length = 3, nullable = false)
    private CurrencyCodeEnum currencyCode = CurrencyCodeEnum.COP;

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
