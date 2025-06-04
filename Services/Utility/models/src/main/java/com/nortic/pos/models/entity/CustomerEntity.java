package com.nortic.pos.models.entity;

import com.nortic.pos.models.enums.DocumentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customers")
class CustomerEntity {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private DocumentTypeEnum documentType;

    @Column(unique = true)
    private String documentNumber;

    @Column(unique = true)
    private String email;

    private String phone;

    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<SaleEntity> sales;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.id = this.id == null ? UUID.randomUUID() : this.id;
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.deletedAt = LocalDateTime.now();
    }
}
