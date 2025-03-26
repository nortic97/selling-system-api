package com.nortic.pos.es.stores.service;


import com.nortic.pos.models.dto.StoreDTO;

public interface IStoreSvc {
    StoreDTO createStore(StoreDTO storeDTO);
}
