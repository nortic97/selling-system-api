package com.nortic.pos.es.store.service.contract;

import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.entity.StoreEntity;

import java.util.UUID;

public interface StoreValidationService {

    StoreEntity validateAndGetStore(UUID id);

    void validateNewStore(StoreDTO storeDTO);

    void validateEmail(String email, UUID id);

    void validatePhone(String phone, UUID id);

}
