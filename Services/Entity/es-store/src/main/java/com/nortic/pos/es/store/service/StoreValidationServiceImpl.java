package com.nortic.pos.es.store.service;

import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.repository.StoreRepository;
import com.nortic.pos.es.store.service.contract.StoreValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreValidationServiceImpl implements StoreValidationService {

    private final StoreRepository storeRepository;

    @Override
    public void validateNewStore(StoreDTO storeDTO) {

        if (!storeRepository.existsByOwnerIdId(storeDTO.getOwnerId())) {
            throw new RuntimeException("Owner does not exist");
        }

        if(storeRepository.existsByTaxId(storeDTO.getTaxId())) {
            throw new RuntimeException("Tax ID already exists");
        }

        if (storeRepository.existsByPhoneNumber(storeDTO.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        if (storeRepository.existsByEmail(storeDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

    }

}
