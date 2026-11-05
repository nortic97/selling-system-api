package com.nortic.pos.es.store.controller;

import com.nortic.pos.es.store.mapper.StoreRestMapper;
import com.nortic.pos.es.store.model.dto.RequestContextDTO;
import com.nortic.pos.es.store.model.dto.SearchParamsDTO;
import com.nortic.pos.es.store.rest.gen.api.StoreApi;
import com.nortic.pos.es.store.rest.gen.model.*;
import com.nortic.pos.es.store.service.contract.StoreService;
import com.nortic.pos.es.store.util.PageableUtil;
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
public class StoreController implements StoreApi {

    private final StoreService storeService;
    private final StoreRestMapper storeRestMapper;

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> changeEmail(UUID correlationId, String consumerId, UUID id, RestModelEmailRequestDTO restModelEmailRequestDTO) {
        log.info("Change email controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeEmailRequestDTO = storeRestMapper.toChangeEmailRequestDTO(restModelEmailRequestDTO);

        var storeDTO = storeService.changeEmail(requestContextDTO, id, changeEmailRequestDTO);

        log.info("Change email controller completed ... ");

        return ResponseEntity.ok(storeRestMapper.toStoreResponseDTO(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> changePhone(UUID correlationId, String consumerId, UUID id, RestModelPhoneRequestDTO restModelPhoneRequestDTO) {
        log.info("Change phone controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changePhoneRequestDTO = storeRestMapper.toChangePhoneRequestDTO(restModelPhoneRequestDTO);

        var storeDTO = storeService.changePhone(requestContextDTO, id, changePhoneRequestDTO);

        log.info("Change phone controller completed ... ");

        return ResponseEntity.ok(storeRestMapper.toStoreResponseDTO(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> changeStatus(UUID correlationId, String consumerId, UUID id, RestModelStatusRequest restModelStatusRequest) {
        log.info("Change status controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeStatusRequestDTO = storeRestMapper.toChangeStatusRequestDTO(restModelStatusRequest);

        var storeDTO = storeService.changeStatus(requestContextDTO, id, changeStatusRequestDTO);

        log.info("Change status controller completed ... ");

        return ResponseEntity.ok(storeRestMapper.toStoreResponseDTO(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> createStore(UUID correlationId, String consumerId, RestModelStoreRequestDTO restModelStoreRequestDTO) {

        log.info("Create store controller ...");

        var storeDTO = storeRestMapper.toStoreDTO(restModelStoreRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdStoreDTO = storeService.createStore(requestContextDTO, storeDTO);

        log.info("Create store controller completed ...");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(storeRestMapper.toStoreResponseDTO(createdStoreDTO));
    }

    @Override
    public ResponseEntity<Void> deleteStore(UUID correlationId, String consumerId, UUID id) {
        log.info("Delete store controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        storeService.deleteStore(requestContextDTO, id);

        log.info("Delete store controller completed ...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> getStore(UUID correlationId, String consumerId, UUID id) {
        log.info("Get store controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var storeDTO = storeService.getStore(requestContextDTO, id);

        log.info("Get store controller completed ...");
        return ResponseEntity.ok(storeRestMapper.toStoreResponseDTO(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> restoreStore(UUID correlationId, String consumerId, UUID id) {
        log.info("Restore store controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var storeDTO = storeService.restoreStore(requestContextDTO, id);

        log.info("Restore store controller completed ...");

        return ResponseEntity.ok(storeRestMapper.toStoreResponseDTO(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStoreResponseDTO> updateStore(UUID correlationId, String consumerId, UUID id, RestModelUpdateStoreRequestDTO restModelUpdateStoreRequestDTO) {
        log.info("Update store controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var storeDTO = storeRestMapper.toUpdateStoreRequestDTO(restModelUpdateStoreRequestDTO);

        var updatedStoreDTO = storeService.updateStore(requestContextDTO, id, storeDTO);

        log.info("Update store controller completed ... ");

        return ResponseEntity.ok(storeRestMapper.toStoreResponseDTO(updatedStoreDTO));
    }

    @Override
    public ResponseEntity<RestModelPaginatedStoreResponseDTO> getAllStores(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        log.info("List stores controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var storesDTO = storeService.listStores(requestContextDTO, pagination);

        log.info("List stores controller completed ...");

        return ResponseEntity.ok(storeRestMapper.toListStoresResponseDTO(storesDTO));
    }
}
