package com.nortic.pos.es.plan.service.contract;

import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.model.entity.PlanEntity;

import java.util.UUID;

public interface PlanValidationService {

    PlanEntity validateAndGetPlan(UUID id);

    void validateNewPlan(PlanDTO planDTO);

}
