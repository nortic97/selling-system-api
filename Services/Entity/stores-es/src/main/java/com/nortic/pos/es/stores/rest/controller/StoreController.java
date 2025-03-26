package com.nortic.pos.es.stores.rest.controller;

import com.nortic.pos.es.stores.rest.gen.api.StoresApi;
import com.nortic.pos.es.stores.rest.gen.model.RestModelStore;
import com.nortic.pos.es.stores.service.StoreSvcImpl;
import com.nortic.pos.es.stores.util.StoreMapper;
import com.nortic.pos.models.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<>(storeMapper.dtoToRestModel(storeDTO), HttpStatus.CREATED);
    }

}
