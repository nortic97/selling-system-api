package com.nortic.pos.models.entity;

import com.nortic.pos.models.enums.PaymentStatusEnum;
import com.nortic.pos.models.enums.SaleStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sales")
class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @Column(nullable = false)
    private LocalDate date;

    @Column(unique = true, nullable = false)
    private String referenceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biller_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    private Double orderTax;

    private Double orderDiscount;

    private Double shipping;

    private String document;

    private SaleStatusEnum saleStatus;

    private PaymentStatusEnum paymentStatus;

    @Lob
    private String saleNote;

    @Column(nullable = false)
    private Double paid;

    @Column(nullable = false)
    private Double total;

    @ManyToMany(mappedBy = "sales", fetch = FetchType.LAZY)
    private Set<ProductEntity> products;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    private Set<ReturnEntity> returns;
}
