package com.nortic.pos.es.store.service.contract;

import com.nortic.pos.es.store.dto.request.RequestContextDTO;
import com.nortic.pos.es.store.dto.response.StoreResponseDTO;

import java.util.UUID;

public interface StoreService {

    StoreResponseDTO getStore(RequestContextDTO requestContextDTO, UUID id);
}
