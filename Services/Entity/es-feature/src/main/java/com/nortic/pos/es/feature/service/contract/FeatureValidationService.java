package com.nortic.pos.es.feature.service.contract;

import com.nortic.pos.es.feature.model.dto.FeatureDTO;
import com.nortic.pos.es.feature.model.entity.FeatureEntity;

import java.util.UUID;

public interface FeatureValidationService {

    FeatureEntity validateAndGetFeature(UUID id);

    void validateNewFeature(FeatureDTO featureDTO);

}
