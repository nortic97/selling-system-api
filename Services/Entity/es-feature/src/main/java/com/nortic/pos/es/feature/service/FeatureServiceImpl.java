package com.nortic.pos.es.feature.service;

import com.nortic.pos.es.feature.exception.BusinessException;
import com.nortic.pos.es.feature.exception.ExceptionCode;
import com.nortic.pos.es.feature.mapper.FeatureEntityMapper;
import com.nortic.pos.es.feature.model.dto.FeatureDTO;
import com.nortic.pos.es.feature.model.dto.PaginatedFeatureResponseDTO;
import com.nortic.pos.es.feature.model.dto.RequestContextDTO;
import com.nortic.pos.es.feature.model.dto.SearchParamsDTO;
import com.nortic.pos.es.feature.repository.FeatureRepository;
import com.nortic.pos.es.feature.service.contract.FeatureService;
import com.nortic.pos.es.feature.service.contract.FeatureValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    private final FeatureEntityMapper featureEntityMapper;

    private final FeatureValidationService featureValidationService;

    @Override
    public FeatureDTO getFeature(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Get feature by id {} service ...", id);

        var feature = featureValidationService.validateAndGetFeature(id);

        log.info("Get feature by id {} service completed ...", id);

        return featureEntityMapper.toFeatureDTO(feature);
    }

    @Override
    public FeatureDTO createFeature(RequestContextDTO requestContextDTO, FeatureDTO featureDTO) {
        log.info("Create feature service ...");

        featureValidationService.validateNewFeature(featureDTO);

        var featureEntity = featureEntityMapper.toFeatureEntity(featureDTO);
        featureEntity.setIsActive(Boolean.FALSE);

        var savedFeatureEntity = featureRepository.save(featureEntity);

        log.info("Create feature service completed ...");

        return featureEntityMapper.toFeatureDTO(savedFeatureEntity);
    }

    @Override
    public PaginatedFeatureResponseDTO listFeatures(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("List features service ...");

        var features = featureRepository.findAll(searchParamsDTO.getPageable());

        log.info("List features service completed ... ");

        return featureEntityMapper.toListFeatureDTO(features);
    }

    @Override
    @Transactional
    public void deleteFeature(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Delete feature service... ");

        var featureEntity = featureValidationService.validateAndGetFeature(id);

        featureRepository.delete(featureEntity);

        log.info("Delete feature service completed ... ");
    }

    @Override
    @Transactional
    public FeatureDTO restoreFeature(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restore feature service ... ");

        var feature = featureRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new BusinessException(ExceptionCode.FEATURE_NOT_FOUND));

        feature.setDeletedAt(null);

        log.info("Restore feature service completed ... ");
        return featureEntityMapper.toFeatureDTO(feature);
    }

    @Override
    @Transactional
    public FeatureDTO updateFeature(RequestContextDTO requestContextDTO, UUID id, FeatureDTO featureDTO) {
        log.info("Update feature service ... ");

        var featureEntity = featureValidationService.validateAndGetFeature(id);

        featureEntityMapper.updateFeatureFromDTO(featureDTO, featureEntity);

        log.info("Update feature service completed ... ");

        return featureEntityMapper.toFeatureDTO(featureEntity);
    }

}
