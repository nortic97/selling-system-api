package com.nortic.pos.es.store.rest.controller;

import com.nortic.pos.es.store.controller.StoreApi;
import com.nortic.pos.es.store.model.RestModelChangeStoreStatusRequest;
import com.nortic.pos.es.store.model.RestModelStore;
import com.nortic.pos.es.store.service.StoreSvcImpl;
import com.nortic.pos.es.store.util.RestMapper;
import com.nortic.pos.models.dto.StoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
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
        log.info("Create Store Rest Controller Start...");
        StoreDTO storeDTO = storeSvc.createStore(restMapper.restModelToDTO(restModelStore));
        log.info("Create Store Rest Controller End...");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restMapper.dtoToRestModel(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStore> getStoreById(UUID id) {
        log.info("Get Store By Id Rest Controller Start...");
        RestModelStore store = restMapper.dtoToRestModel(storeSvc.getStore(id));
        log.info("Get Store By Id Rest Controller End...");
        return ResponseEntity.ok(store);
    }

    @Override
    public ResponseEntity<RestModelStore> updateStore(UUID id, RestModelStore restModelStore) {
        log.info("Update Store Rest Controller Start...");
        StoreDTO storeDTO = storeSvc.updateStore(id, restMapper.restModelToDTO(restModelStore));
        log.info("Update Store Rest Controller End...");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restMapper.dtoToRestModel(storeDTO));
    }

    @Override
    public ResponseEntity<Void> deleteStore(UUID id) {
        log.info("Delete Store Rest Controller Start...");
        storeSvc.deleteStore(id);
        log.info("Delete Store Rest Controller End...");
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<Void> softDeleteStore(UUID id) {
        log.info("Soft Delete Store Rest Controller Start...");
        storeSvc.softDeleteStore(id);
        log.info("Soft Delete Store Rest Controller End...");
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<Void> changeStoreStatus(UUID id, RestModelChangeStoreStatusRequest restModelChangeStoreStatusRequest) {
        log.info("Change Store Status Rest Controller Start...");
        storeSvc.changeStoreStatus(id, restModelChangeStoreStatusRequest.getIsActive());
        log.info("Change Store Status Rest Controller End...");
        return ResponseEntity.ok().body(null);
    }

}
