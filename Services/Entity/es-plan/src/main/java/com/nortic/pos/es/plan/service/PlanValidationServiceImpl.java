package com.nortic.pos.es.plan.service;

import com.nortic.pos.es.plan.exception.BusinessException;
import com.nortic.pos.es.plan.exception.ExceptionCode;
import com.nortic.pos.es.plan.model.dto.PlanDTO;
import com.nortic.pos.es.plan.model.entity.PlanEntity;
import com.nortic.pos.es.plan.repository.PlanRepository;
import com.nortic.pos.es.plan.service.contract.PlanValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        if (planDTO.getName() == null || planDTO.getName().isBlank()) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }
        if (planDTO.getPrice() == null) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }
        if (planDTO.getTypeOfPlan() == null) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }
    }

}
