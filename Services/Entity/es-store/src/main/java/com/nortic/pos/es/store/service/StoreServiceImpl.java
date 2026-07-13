package com.nortic.pos.es.store.service;

import com.nortic.pos.es.store.dto.request.RequestContextDTO;
import com.nortic.pos.es.store.dto.response.StoreResponseDTO;
import com.nortic.pos.es.store.repository.StoreRepository;
import com.nortic.pos.es.store.service.contract.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public StoreResponseDTO getStore(RequestContextDTO requestContextDTO, UUID id) {
        return null;
    }

}
