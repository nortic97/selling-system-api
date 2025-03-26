package com.nortic.pos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "stores")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String taxId;
    @Column(nullable = false)
    private String name;
    private String logo;
    private String description;
    private String slogan;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String stateProvince;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false, unique = true)
    private String email;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        deletedAt = LocalDateTime.now();
    }
}
