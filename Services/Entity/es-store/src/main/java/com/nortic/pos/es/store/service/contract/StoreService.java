package com.nortic.pos.es.store.service.contract;

import com.nortic.pos.es.store.model.dto.*;

import java.util.UUID;

public interface StoreService {

    StoreDTO getStore(RequestContextDTO requestContextDTO, UUID id);

    StoreDTO createStore(RequestContextDTO requestContextDTO, StoreDTO storeDTO);

    PaginatedStoreResponseDTO listStores(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deleteStore(RequestContextDTO requestContextDTO, UUID id);

    StoreDTO restoreStore(RequestContextDTO requestContextDTO, UUID id);

    StoreDTO updateStore(RequestContextDTO requestContextDTO, UUID id, StoreDTO storeDTO);

    StoreDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, ChangeStatusRequestDTO changeStatusRequestDTO);

    StoreDTO changeEmail(RequestContextDTO requestContextDTO, UUID id, ChangeEmailRequestDTO changeEmailRequestDTO);

    StoreDTO changePhone(RequestContextDTO requestContextDTO, UUID id, ChangePhoneRequestDTO changePhoneRequestDTO);

}
