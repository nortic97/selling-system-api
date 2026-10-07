package com.nortic.pos.es.plan.model.dto;

import com.nortic.pos.es.plan.model.enums.PlanTypeOfPlanEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private PlanTypeOfPlanEnum typeOfPlan;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;

}
