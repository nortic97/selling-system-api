package com.nortic.pos.es.store.service;

import com.nortic.pos.es.store.dto.SearchParamsDTO;
import com.nortic.pos.es.store.dto.request.RequestContextDTO;
import com.nortic.pos.es.store.dto.response.PaginatedStoreResponseDTO;
import com.nortic.pos.es.store.dto.response.StoreDTO;
import com.nortic.pos.es.store.mapper.StoreEntityMapper;
import com.nortic.pos.es.store.repository.StoreRepository;
import com.nortic.pos.es.store.service.contract.StoreService;
import com.nortic.pos.es.store.service.contract.StoreValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private final StoreEntityMapper storeEntityMapper;

    private final StoreValidationService storeValidationService;

    @Override
    public StoreDTO getStore(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Get store by id {} service ...", id);

        var store = storeValidationService.validateAndGetStore(id);

        log.info("Get store by id {} service completed ...", id);

        return storeEntityMapper.toStoreDTO(store);
    }

    @Override
    public StoreDTO createStore(RequestContextDTO requestContextDTO, StoreDTO storeDTO) {
        log.info("Create store service ...");

        storeValidationService.validateNewStore(storeDTO);

        var storeEntity = storeEntityMapper.toStoreEntity(storeDTO);
        storeEntity.setIsActive(Boolean.FALSE);

        var savedStoreEntity = storeRepository.save(storeEntity);

        log.info("Create store service completed ...");

        return storeEntityMapper.toStoreDTO(savedStoreEntity);
    }

    @Override
    public PaginatedStoreResponseDTO listStores(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("List stores service ...");

        var stores = storeRepository.findAll(searchParamsDTO.getPageable());

        log.info("List stores service completed ... ");

        return storeEntityMapper.toListStoreDTO(stores);
    }

}
