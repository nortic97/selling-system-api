package com.nortic.pos.es.store.rest.controller;

import com.nortic.pos.es.store.rest.gen.api.StoreApi;
import com.nortic.pos.es.store.rest.gen.model.RestModelChangeStoreStatusRequest;
import com.nortic.pos.es.store.rest.gen.model.RestModelStore;
import com.nortic.pos.es.store.service.StoreSvcImpl;
import com.nortic.pos.es.store.util.RestMapper;
import com.nortic.pos.models.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RestControllerImpl implements StoreApi {

    private final RestMapper restMapper;
    private final StoreSvcImpl storeSvc;

    @Autowired
    public RestControllerImpl(RestMapper restMapper, StoreSvcImpl storeSvc) {
        this.restMapper = restMapper;
        this.storeSvc = storeSvc;
    }

    @Override
    public ResponseEntity<RestModelStore> createStore(RestModelStore restModelStore) {
        StoreDTO storeDTO = storeSvc.createStore(restMapper.restModelToDTO(restModelStore));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restMapper.dtoToRestModel(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStore> getStoreById(UUID id) {
        return ResponseEntity.ok(restMapper.dtoToRestModel(storeSvc.getStore(id)));
    }

    @Override
    public ResponseEntity<RestModelStore> updateStore(UUID id, RestModelStore restModelStore) {
        StoreDTO storeDTO = storeSvc.updateStore(id, restMapper.restModelToDTO(restModelStore));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restMapper.dtoToRestModel(storeDTO));
    }

    @Override
    public ResponseEntity<Void> deleteStore(UUID id) {
        storeSvc.deleteStore(id);
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<Void> softDeleteStore(UUID id) {
        storeSvc.softDeleteStore(id);
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<Void> changeStoreStatus(UUID id, RestModelChangeStoreStatusRequest restModelChangeStoreStatusRequest) {
        storeSvc.changeStoreStatus(id, restModelChangeStoreStatusRequest.getIsActive());
        return ResponseEntity.ok().body(null);
    }

}
