package com.nortic.pos.es.plan.service;

import com.nortic.pos.es.plan.exception.BusinessException;
import com.nortic.pos.es.plan.exception.ExceptionCode;
import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.model.entity.PlanEntity;
import com.nortic.pos.es.plan.repository.PlanRepository;
import com.nortic.pos.es.plan.service.contract.PlanValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanValidationServiceImpl implements PlanValidationService {

    private final PlanRepository planRepository;

    @Override
    public PlanEntity validateAndGetPlan(UUID id) {
        return planRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.PLAN_NOT_FOUND));
    }

    @Override
    public void validateNewPlan(PlanDTO planDTO) {
        // Add validation logic as needed
    }

}
