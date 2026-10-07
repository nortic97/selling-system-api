package com.nortic.pos.es.plan.service;

import com.nortic.pos.es.plan.exception.BusinessException;
import com.nortic.pos.es.plan.exception.ExceptionCode;
import com.nortic.pos.es.plan.mapper.PlanEntityMapper;
import com.nortic.pos.es.plan.model.dto.PaginatedPlanResponseDTO;
import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.model.dto.RequestContextDTO;
import com.nortic.pos.es.plan.model.dto.SearchParamsDTO;
import com.nortic.pos.es.plan.repository.PlanRepository;
import com.nortic.pos.es.plan.service.contract.PlanService;
import com.nortic.pos.es.plan.service.contract.PlanValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    private final PlanEntityMapper planEntityMapper;

    private final PlanValidationService planValidationService;

    @Override
    public PlanDTO getPlan(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Get plan by id {} service ...", id);

        var plan = planValidationService.validateAndGetPlan(id);

        log.info("Get plan by id {} service completed ...", id);

        return planEntityMapper.toPlanDTO(plan);
    }

    @Override
    public PlanDTO createPlan(RequestContextDTO requestContextDTO, PlanDTO planDTO) {
        log.info("Create plan service ...");

        planValidationService.validateNewPlan(planDTO);

        var planEntity = planEntityMapper.toPlanEntity(planDTO);

        var savedPlanEntity = planRepository.save(planEntity);

        log.info("Create plan service completed ...");

        return planEntityMapper.toPlanDTO(savedPlanEntity);
    }

    @Override
    public PaginatedPlanResponseDTO listPlans(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("List plans service ...");

        var plans = planRepository.findAll(searchParamsDTO.getPageable());

        log.info("List plans service completed ... ");

        return planEntityMapper.toListPlanDTO(plans);
    }

    @Override
    @Transactional
    public void deletePlan(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Delete plan service... ");

        var planEntity = planValidationService.validateAndGetPlan(id);

        planRepository.delete(planEntity);

        log.info("Delete plan service completed ... ");
    }

    @Override
    @Transactional
    public PlanDTO restorePlan(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restore plan service ... ");

        var plan = planRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new BusinessException(ExceptionCode.PLAN_NOT_FOUND));

        plan.setDeletedAt(null);

        log.info("Restore plan service completed ... ");
        return planEntityMapper.toPlanDTO(plan);
    }

    @Override
    @Transactional
    public PlanDTO updatePlan(RequestContextDTO requestContextDTO, UUID id, PlanDTO planDTO) {
        log.info("Update plan service ... ");

        var planEntity = planValidationService.validateAndGetPlan(id);

        planEntityMapper.updatePlanFromDTO(planDTO, planEntity);

        log.info("Update plan service completed ... ");

        return planEntityMapper.toPlanDTO(planEntity);
    }

}
