package com.nortic.pos.es.feature.controller;

import com.nortic.pos.es.feature.rest.gen.api.FeatureApi;
import com.nortic.pos.es.feature.rest.gen.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FeatureController implements FeatureApi {
    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> changeStatus(UUID correlationId, String consumerId, UUID id, RestModelStatusRequest restModelStatusRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> createFeature(UUID correlationId, String consumerId, RestModelFeatureRequestDTO restModelFeatureRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteFeature(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelPaginatedFeatureResponseDTO> getAllFeatures(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> getFeature(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> restoreFeature(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelFeatureResponseDTO> updateFeature(UUID correlationId, String consumerId, UUID id, RestModelUpdateFeatureRequestDTO restModelUpdateFeatureRequestDTO) {
        return null;
    }
}
