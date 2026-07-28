package com.nortic.pos.es.store.service.contract;

import com.nortic.pos.es.store.dto.SearchParamsDTO;
import com.nortic.pos.es.store.dto.request.RequestContextDTO;
import com.nortic.pos.es.store.dto.response.PaginatedStoreResponseDTO;
import com.nortic.pos.es.store.dto.response.StoreDTO;

import java.util.UUID;

public interface StoreService {

    StoreDTO getStore(RequestContextDTO requestContextDTO, UUID id);

    StoreDTO createStore(RequestContextDTO requestContextDTO, StoreDTO storeDTO);

    PaginatedStoreResponseDTO listStores(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deleteStore(RequestContextDTO requestContextDTO, UUID id);
}
