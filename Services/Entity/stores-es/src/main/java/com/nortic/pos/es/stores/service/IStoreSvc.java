package com.nortic.pos.es.stores.service;


import com.nortic.pos.models.dto.StoreDTO;

import java.util.UUID;

public interface IStoreSvc {
    StoreDTO createStore(StoreDTO storeDTO);
    StoreDTO getStore(UUID id);
    StoreDTO updateStore(UUID id, StoreDTO storeDTO);
    void deleteStore(UUID id);
    void softDeleteStore(UUID id);
    void changeStoreStatus(UUID id, Boolean status);
}
