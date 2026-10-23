package com.nortic.pos.es.store.service;

import com.nortic.pos.es.store.exception.BusinessException;
import com.nortic.pos.es.store.exception.ExceptionCode;
import com.nortic.pos.es.store.mapper.StoreEntityMapper;
import com.nortic.pos.es.store.model.dto.*;
import com.nortic.pos.es.store.repository.StoreRepository;
import com.nortic.pos.es.store.service.contract.StoreService;
import com.nortic.pos.es.store.service.contract.StoreValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        storeEntity.setIsActive(Boolean.TRUE);

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

    @Override
    @Transactional
    public void deleteStore(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Delete store service... ");

        var storeEntity = storeValidationService.validateAndGetStore(id);

        storeRepository.delete(storeEntity);

        log.info("Delete store service completed ... ");
    }

    @Override
    @Transactional
    public StoreDTO restoreStore(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restore store service ... ");

        var store = storeRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new BusinessException(ExceptionCode.STORE_NOT_FOUND));

        store.setIsActive(Boolean.TRUE);
        store.setDeletedAt(null);

        log.info("Restore store service completed ... ");
        return storeEntityMapper.toStoreDTO(store);
    }

    @Override
    @Transactional
    public StoreDTO updateStore(RequestContextDTO requestContextDTO, UUID id, StoreDTO storeDTO) {
        log.info("Update store service ... ");

        var storeEntity = storeValidationService.validateAndGetStore(id);

        storeValidationService.validateStoreUpdate(id, storeDTO);

        storeEntityMapper.updateStoreFromDTO(storeDTO, storeEntity);

        log.info("Update store service completed ... ");

        return storeEntityMapper.toStoreDTO(storeEntity);
    }

    @Override
    @Transactional
    public StoreDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeStatusRequestDTO changeStatusRequestDTO) {
        log.info("Change status service ... ");

        var storeEntity = storeValidationService.validateAndGetStore(id);

        storeEntity.setIsActive(changeStatusRequestDTO.getActive());

        log.info("Change status service completed ... ");

        return storeEntityMapper.toStoreDTO(storeEntity);
    }

    @Override
    @Transactional
    public StoreDTO changeEmail(RequestContextDTO requestContextDTO, UUID id, ChangeEmailRequestDTO changeEmailRequestDTO) {
        log.info("Change email service ... ");

        var storeEntity = storeValidationService.validateAndGetStore(id);

        storeValidationService.validateEmail(id, changeEmailRequestDTO.getContactEmail());

        storeEntity.setContactEmail(changeEmailRequestDTO.getContactEmail() != null
                ? changeEmailRequestDTO.getContactEmail().trim().toLowerCase()
                : null);

        log.info("Change email service completed ... ");

        return storeEntityMapper.toStoreDTO(storeEntity);
    }

    @Override
    @Transactional
    public StoreDTO changePhone(RequestContextDTO requestContextDTO, UUID id, ChangePhoneRequestDTO changePhoneRequestDTO) {
        log.info("Change phone service ... ");

        var storeEntity = storeValidationService.validateAndGetStore(id);

        storeValidationService.validatePhone(id, changePhoneRequestDTO.getPhoneNumber());

        storeEntity.setPhoneNumber(changePhoneRequestDTO.getPhoneNumber() != null
                ? changePhoneRequestDTO.getPhoneNumber().trim()
                : null);

        log.info("Change phone service completed ... ");

        return storeEntityMapper.toStoreDTO(storeEntity);
    }
}
