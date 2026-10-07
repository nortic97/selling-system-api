package com.nortic.pos.es.plan.controller;

import com.nortic.pos.es.plan.mapper.PlanRestMapper;
import com.nortic.pos.es.plan.model.dto.RequestContextDTO;
import com.nortic.pos.es.plan.model.dto.SearchParamsDTO;
import com.nortic.pos.es.plan.rest.gen.api.PlanApi;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPlanRequestDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelPlanResponseDTO;
import com.nortic.pos.es.plan.rest.gen.model.RestModelUpdatePlanRequestDTO;
import com.nortic.pos.es.plan.service.contract.PlanService;
import com.nortic.pos.es.plan.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PlanController implements PlanApi {

    private final PlanService planService;
    private final PlanRestMapper planRestMapper;

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> createPlan(UUID correlationId, String consumerId, RestModelPlanRequestDTO restModelPlanRequestDTO) {
        log.info("Create plan controller ...");

        var planDTO = planRestMapper.toPlanDTO(restModelPlanRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdPlanDTO = planService.createPlan(requestContextDTO, planDTO);

        log.info("Create plan controller completed ...");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(planRestMapper.toPlanResponseDTO(createdPlanDTO));
    }

    @Override
    public ResponseEntity<Void> deletePlan(UUID correlationId, String consumerId, UUID id) {
        log.info("Delete plan controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        planService.deletePlan(requestContextDTO, id);

        log.info("Delete plan controller completed ...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedPlanResponseDTO> getAllPlans(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        log.info("List plans controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var plansDTO = planService.listPlans(requestContextDTO, pagination);

        log.info("List plans controller completed ...");

        return ResponseEntity.ok(planRestMapper.toListPlansResponseDTO(plansDTO));
    }

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> getPlan(UUID correlationId, String consumerId, UUID id) {
        log.info("Get plan controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var planDTO = planService.getPlan(requestContextDTO, id);

        log.info("Get plan controller completed ...");
        return ResponseEntity.ok(planRestMapper.toPlanResponseDTO(planDTO));
    }

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> restorePlan(UUID correlationId, String consumerId, UUID id) {
        log.info("Restore plan controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var planDTO = planService.restorePlan(requestContextDTO, id);

        log.info("Restore plan controller completed ...");

        return ResponseEntity.ok(planRestMapper.toPlanResponseDTO(planDTO));
    }

    @Override
    public ResponseEntity<RestModelPlanResponseDTO> updatePlan(UUID correlationId, String consumerId, UUID id, RestModelUpdatePlanRequestDTO restModelUpdatePlanRequestDTO) {
        log.info("Update plan controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var planDTO = planRestMapper.toUpdatePlanRequestDTO(restModelUpdatePlanRequestDTO);

        var updatedPlanDTO = planService.updatePlan(requestContextDTO, id, planDTO);

        log.info("Update plan controller completed ... ");

        return ResponseEntity.ok(planRestMapper.toPlanResponseDTO(updatedPlanDTO));
    }
}
