package com.nortic.pos.es.feature.service;

import com.nortic.pos.es.feature.exception.BusinessException;
import com.nortic.pos.es.feature.exception.ExceptionCode;
import com.nortic.pos.es.feature.model.dto.FeatureDTO;
import com.nortic.pos.es.feature.model.entity.FeatureEntity;
import com.nortic.pos.es.feature.repository.FeatureRepository;
import com.nortic.pos.es.feature.service.contract.FeatureValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeatureValidationServiceImpl implements FeatureValidationService {

    private final FeatureRepository featureRepository;

    @Override
    public FeatureEntity validateAndGetFeature(UUID id) {
        return featureRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.FEATURE_NOT_FOUND));
    }

    @Override
    public void validateNewFeature(FeatureDTO featureDTO) {
        if (featureDTO.getCode() == null || featureDTO.getCode().isBlank()) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }
        if (featureDTO.getName() == null || featureDTO.getName().isBlank()) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }
        if (featureDTO.getType() == null) {
            throw new BusinessException(ExceptionCode.VALIDATION_FAILED);
        }
    }

}
