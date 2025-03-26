package com.nortic.pos.es.stores.service;

import com.nortic.pos.es.stores.repository.StoreRepository;
import com.nortic.pos.es.stores.util.StoreMapper;
import com.nortic.pos.models.dto.StoreDTO;
import com.nortic.pos.models.entity.StoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreSvcImpl implements IStoreSvc {

    private final StoreMapper storeMapper;
    private final StoreRepository storeRepository;

    @Autowired
    public StoreSvcImpl(StoreMapper storeMapper, StoreRepository storeRepository) {
        this.storeMapper = storeMapper;
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreDTO createStore(StoreDTO storeDTO) {
        StoreEntity store = storeMapper.dtoToEntity(storeDTO);
        return storeMapper.entityToDTO(storeRepository.save(store));
    }
}
