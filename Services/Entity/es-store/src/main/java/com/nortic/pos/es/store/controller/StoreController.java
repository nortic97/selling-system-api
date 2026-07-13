package com.nortic.pos.es.store.controller;

import com.nortic.pos.es.store.rest.gen.api.StoreApi;
import com.nortic.pos.es.store.rest.gen.model.*;
import com.nortic.pos.es.store.service.contract.StoreService;
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
public class StoreController implements StoreApi {

    private final StoreService storeService;

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> changeEmail(UUID correlationId, String consumerId, UUID id, RestModelEmailRequestDTO restModelEmailRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> changePhone(UUID correlationId, String consumerId, UUID id, RestModelPhoneRequestDTO restModelPhoneRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> changeStatus(UUID correlationId, String consumerId, UUID id, RestModelStatusRequest restModelStatusRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> createStore(UUID correlationId, String consumerId, RestModelStoreRequestDTO restModelStoreRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteStore(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> getStore(UUID correlationId, String consumerId, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RestModelListStores200Response> listStores(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        return null;
    }
}
