package com.nortic.pos.es.feature.controller;

import com.nortic.pos.es.feature.mapper.FeatureRestMapper;
import com.nortic.pos.es.feature.model.dto.RequestContextDTO;
import com.nortic.pos.es.feature.model.dto.SearchParamsDTO;
import com.nortic.pos.es.feature.rest.gen.api.FeatureApi;
import com.nortic.pos.es.feature.rest.gen.model.*;
import com.nortic.pos.es.feature.service.contract.FeatureService;
import com.nortic.pos.es.feature.util.PageableUtil;
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
public class FeatureController implements FeatureApi {

    private final FeatureService featureService;
    private final FeatureRestMapper featureRestMapper;

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> changeStatus(UUID correlationId, String consumerId, UUID id, RestModelStatusRequest restModelStatusRequest) {
        log.info("Change status feature controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var featureDTO = featureService.getFeature(requestContextDTO, id);

        featureDTO.setIsActive(restModelStatusRequest.getIsActive());

        var updatedFeatureDTO = featureService.updateFeature(requestContextDTO, id, featureDTO);

        log.info("Change status feature controller completed ...");

        return ResponseEntity.ok(featureRestMapper.toFeatureResponseDTO(updatedFeatureDTO));
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> createFeature(UUID correlationId, String consumerId, RestModelFeatureRequestDTO restModelFeatureRequestDTO) {
        log.info("Create feature controller ...");

        var featureDTO = featureRestMapper.toFeatureDTO(restModelFeatureRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdFeatureDTO = featureService.createFeature(requestContextDTO, featureDTO);

        log.info("Create feature controller completed ...");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(featureRestMapper.toFeatureResponseDTO(createdFeatureDTO));
    }

    @Override
    public ResponseEntity<Void> deleteFeature(UUID correlationId, String consumerId, UUID id) {
        log.info("Delete feature controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        featureService.deleteFeature(requestContextDTO, id);

        log.info("Delete feature controller completed ...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedFeatureResponseDTO> getAllFeatures(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        log.info("List features controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var featuresDTO = featureService.listFeatures(requestContextDTO, pagination);

        log.info("List features controller completed ...");

        return ResponseEntity.ok(featureRestMapper.toListFeaturesResponseDTO(featuresDTO));
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> getFeature(UUID correlationId, String consumerId, UUID id) {
        log.info("Get feature controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var featureDTO = featureService.getFeature(requestContextDTO, id);

        log.info("Get feature controller completed ...");
        return ResponseEntity.ok(featureRestMapper.toFeatureResponseDTO(featureDTO));
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> restoreFeature(UUID correlationId, String consumerId, UUID id) {
        log.info("Restore feature controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var featureDTO = featureService.restoreFeature(requestContextDTO, id);

        log.info("Restore feature controller completed ...");

        return ResponseEntity.ok(featureRestMapper.toFeatureResponseDTO(featureDTO));
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> updateFeature(UUID correlationId, String consumerId, UUID id, RestModelUpdateFeatureRequestDTO restModelUpdateFeatureRequestDTO) {
        log.info("Update feature controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var featureDTO = featureRestMapper.toUpdateFeatureRequestDTO(restModelUpdateFeatureRequestDTO);

        var updatedFeatureDTO = featureService.updateFeature(requestContextDTO, id, featureDTO);

        log.info("Update feature controller completed ... ");

        return ResponseEntity.ok(featureRestMapper.toFeatureResponseDTO(updatedFeatureDTO));
    }
}
