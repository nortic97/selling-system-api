package com.nortic.pos.es.stores.rest.controller;

import com.nortic.pos.es.stores.rest.gen.api.StoresApi;
import com.nortic.pos.es.stores.rest.gen.model.RestModelChangeStoreStatusRequest;
import com.nortic.pos.es.stores.rest.gen.model.RestModelStore;
import com.nortic.pos.es.stores.service.StoreSvcImpl;
import com.nortic.pos.es.stores.util.StoreMapper;
import com.nortic.pos.models.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class StoreController implements StoresApi {

    private final StoreMapper storeMapper;
    private final StoreSvcImpl storeSvc;

    @Autowired
    public StoreController(StoreMapper storeMapper, StoreSvcImpl storeSvc) {
        this.storeMapper = storeMapper;
        this.storeSvc = storeSvc;
    }

    @Override
    public ResponseEntity<RestModelStore> createStore(RestModelStore restModelStore) {
        StoreDTO storeDTO = storeSvc.createStore(storeMapper.restModelToDTO(restModelStore));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(storeMapper.dtoToRestModel(storeDTO));
    }

    @Override
    public ResponseEntity<RestModelStore> getStoreById(UUID id) {
        return ResponseEntity.ok(storeMapper.dtoToRestModel(storeSvc.getStore(id)));
    }

    @Override
    public ResponseEntity<RestModelStore> updateStore(UUID id, RestModelStore restModelStore) {
        StoreDTO storeDTO = storeSvc.updateStore(id, storeMapper.restModelToDTO(restModelStore));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storeMapper.dtoToRestModel(storeDTO));
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
