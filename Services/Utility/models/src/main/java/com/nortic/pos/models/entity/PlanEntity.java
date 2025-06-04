package com.nortic.pos.models.entity;

import com.nortic.pos.models.enums.TypeOfPlanEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "plans")
public class PlanEntity {

    @Id
    private UUID id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    @Enumerated(EnumType.STRING)
    private TypeOfPlanEnum typeOfPlan;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserPlanPaymentEntity> userPlanPayments;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.id = this.id == null ? UUID.randomUUID() : this.id;
    }
}
