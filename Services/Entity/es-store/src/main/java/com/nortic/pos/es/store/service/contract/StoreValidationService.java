package com.nortic.pos.es.store.service.contract;

import com.nortic.pos.es.store.model.dto.StoreDTO;
import com.nortic.pos.es.store.model.entity.StoreEntity;

import java.util.UUID;

public interface StoreValidationService {

    StoreEntity validateAndGetStore(UUID id);

    void validateNewStore(StoreDTO storeDTO);

    void validateStoreUpdate(UUID id, StoreDTO storeDTO);

    void validateEmail(UUID id, String email);

    void validatePhone(UUID id, String phone);
}
