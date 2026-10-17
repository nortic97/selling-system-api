package com.nortic.pos.es.feature.service.contract;

import com.nortic.pos.es.feature.model.dto.FeatureDTO;
import com.nortic.pos.es.feature.model.dto.PaginatedFeatureResponseDTO;
import com.nortic.pos.es.feature.model.dto.RequestContextDTO;
import com.nortic.pos.es.feature.model.dto.SearchParamsDTO;

import java.util.UUID;

public interface FeatureService {

    FeatureDTO getFeature(RequestContextDTO requestContextDTO, UUID id);

    FeatureDTO createFeature(RequestContextDTO requestContextDTO, FeatureDTO featureDTO);

    PaginatedFeatureResponseDTO listFeatures(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deleteFeature(RequestContextDTO requestContextDTO, UUID id);

    FeatureDTO restoreFeature(RequestContextDTO requestContextDTO, UUID id);

    FeatureDTO updateFeature(RequestContextDTO requestContextDTO, UUID id, FeatureDTO featureDTO);

}
