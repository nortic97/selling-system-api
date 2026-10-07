package com.nortic.pos.es.plan.service.contract;

import com.nortic.pos.es.plan.model.dto.PaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.model.dto.RequestContextDTO;
import com.nortic.pos.es.plan.model.dto.SearchParamsDTO;

import java.util.UUID;

public interface PlanService {

    PlanDTO getPlan(RequestContextDTO requestContextDTO, UUID id);

    PlanDTO createPlan(RequestContextDTO requestContextDTO, PlanDTO planDTO);

    PaginatedPlanResponseDTO listPlans(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deletePlan(RequestContextDTO requestContextDTO, UUID id);

    PlanDTO restorePlan(RequestContextDTO requestContextDTO, UUID id);

    PlanDTO updatePlan(RequestContextDTO requestContextDTO, UUID id, PlanDTO planDTO);

}
