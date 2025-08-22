package com.nortic.pos.es.store.service;

import com.nortic.pos.es.store.repository.StoreRepository;
import com.nortic.pos.es.store.util.CustomException;
import com.nortic.pos.es.store.util.RestMapper;
import com.nortic.pos.models.dto.StoreDTO;
import com.nortic.pos.models.entity.StoreEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class StoreSvcImpl implements IStoreSvc {

    private final RestMapper restMapper;
    private final StoreRepository storeRepository;

    @Autowired
    public StoreSvcImpl(RestMapper restMapper, StoreRepository storeRepository) {
        this.restMapper = restMapper;
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreDTO createStore(StoreDTO storeDTO) {
        log.info("Create Store Service Start...");
        log.info("Creating new store");
        StoreEntity store = restMapper.dtoToEntity(storeDTO);
        StoreDTO result = restMapper.entityToDTO(storeRepository.save(store));
        log.info("Create Store Service End...");
        return result;
    }

    @Override
    public StoreDTO getStore(UUID id) {
        log.info("Get Store Service Start...");
        log.info("Retrieving store with id {}", id);
        StoreDTO result = storeRepository.findById(id)
                .map(restMapper::entityToDTO)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));
        log.info("Get Store Service End...");
        return result;
    }

    @Override
    public StoreDTO updateStore(UUID id, StoreDTO storeDTO) {
        log.info("Update Store Service Start...");
        log.info("Updating store with id {}", id);

        StoreEntity storeEntity = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        storeEntity.setTaxId(storeDTO.getTaxId());
        storeEntity.setName(storeDTO.getName());
        storeEntity.setLogo(storeDTO.getLogo());
        storeEntity.setDescription(storeDTO.getDescription());
        storeEntity.setSlogan(storeDTO.getSlogan());
        storeEntity.setAddress(storeDTO.getAddress());
        storeEntity.setStateProvince(storeDTO.getStateProvince());
        storeEntity.setCity(storeDTO.getCity());
        storeEntity.setPhoneNumber(storeDTO.getPhoneNumber());

        StoreDTO result = restMapper.entityToDTO(storeRepository.save(storeEntity));
        log.info("Update Store Service End...");
        return result;
    }

    @Override
    public void deleteStore(UUID id) {
        log.info("Delete Store Service Start...");
        log.info("Deleting store with id {}", id);
        if (!storeRepository.existsById(id)) {
            throw new CustomException(404, "ID: " + id + " Not Found");
        }
        storeRepository.deleteById(id);
        log.info("Delete Store Service End...");
    }

    @Override
    public void softDeleteStore(UUID id) {
        log.info("Soft Delete Store Service Start...");
        log.info("Mark as deleted store with id {}", id);

        StoreEntity storeEntity = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));


        if (storeEntity.getDeletedAt() != null) {
            log.error("Store with ID: {} is already deleted", id);
            throw new CustomException(409, "Store with ID: " + id + " is already deleted");
        }

        storeEntity.markAsDeleted();

        storeRepository.save(storeEntity);
        log.info("Soft Delete Store Service End...");
    }

    @Override
    public void changeStoreStatus(UUID id, Boolean status) {
        log.info("Change Store Status Service Start...");
        log.info("Changing store status with id {} to status {}", id, status);

        StoreEntity storeEntity = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        if (Objects.equals(storeEntity.getIsActive(), status)) {
            log.warn("Store with id {} already has status {}", id, status);
            throw new CustomException(409, "Store with id " + id + " already has status " + status);
        }

        storeEntity.changeStatus(status);
        storeRepository.save(storeEntity);
        log.info("Change Store Status Service End...");
    }
}
