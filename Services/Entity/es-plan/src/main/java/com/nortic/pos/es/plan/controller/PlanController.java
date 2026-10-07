package com.nortic.pos.es.plan.controller;

import com.nortic.pos.es.plan.rest.gen.api.PlanApi;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPlanRequestDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPlanResponseDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelUpdatePlanRequestDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
public class PlanController implements PlanApi {

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> createPlan(UUID correlationId, String consumerId, RestModelPlanRequestDTO restModelPlanRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePlan(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPaginatedPlanResponseDTO> getAllPlans(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> getPlan(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> restorePlan(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> updatePlan(UUID correlationId, String consumerId, UUID id, RestModelUpdatePlanRequestDTO restModelUpdatePlanRequestDTO) {
        return null;
    }
}
