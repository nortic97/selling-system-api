package com.nortic.pos.models.entity;

import com.nortic.pos.models.enums.TypeOfPlanEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "plans")
public class PlansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private TypeOfPlanEnum typeOfPlan;

}
