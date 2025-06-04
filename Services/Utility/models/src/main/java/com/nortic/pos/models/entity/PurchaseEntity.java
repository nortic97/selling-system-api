package com.nortic.pos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "purchases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseEntity {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    private LocalDate date;

    @Column(nullable = false, unique = true)
    private String referenceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierEntity supplier;

    private LocalDate received;

    private Double orderTax;

    private Double discount;

    private Double shipping;

    private String document;

    @Lob
    private String notes;

    private Double paid;

    @Column(nullable = false)
    private Double total;

    @PrePersist
    protected void onCreate() {
        this.id = this.id == null ? UUID.randomUUID() : this.id;
    }
}
