package com.nortic.pos.es.store.service.contract;

import com.nortic.pos.es.store.dto.response.StoreDTO;

public interface StoreValidationService {

    void validateNewStore(StoreDTO storeDTO);

}
