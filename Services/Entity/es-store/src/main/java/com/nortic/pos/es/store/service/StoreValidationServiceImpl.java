package com.nortic.pos.es.store.service;

import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.entity.StoreEntity;
import com.nortic.pos.es.store.exception.BusinessException;
import com.nortic.pos.es.store.exception.ExceptionCode;
import com.nortic.pos.es.store.repository.StoreRepository;
import com.nortic.pos.es.store.service.contract.StoreValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreValidationServiceImpl implements StoreValidationService {

    private final StoreRepository storeRepository;

    @Override
    public StoreEntity validateAndGetStore(UUID id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionCode.STORE_NOT_FOUND)
                );
    }

    @Override
    public void validateNewStore(StoreDTO storeDTO) {

        if (!storeRepository.existsByOwnerIdId(storeDTO.getOwnerId())) {
            log.error("Owner {} does not exist", storeDTO.getOwnerId());
            throw new BusinessException(ExceptionCode.OWNER_NOT_FOUND);
        }

        if (storeRepository.existsByTaxId(storeDTO.getTaxId())) {
            log.error("Tax ID {} already exists", storeDTO.getTaxId());
            throw new BusinessException(ExceptionCode.TAX_ID_ALREADY_EXISTS);
        }

        if (storeRepository.existsByPhoneNumber(storeDTO.getPhoneNumber())) {
            log.error("Phone number {} already exists", storeDTO.getPhoneNumber());
            throw new BusinessException(ExceptionCode.PHONE_ALREADY_EXISTS);
        }

        if (storeRepository.existsByEmail(storeDTO.getEmail())) {
            log.error("Email {} already exists", storeDTO.getEmail());
            throw new BusinessException(ExceptionCode.EMAIL_ALREADY_EXISTS);
        }

    }

}
