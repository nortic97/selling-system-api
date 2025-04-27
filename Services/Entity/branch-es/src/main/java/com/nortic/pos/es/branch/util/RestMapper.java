package com.nortic.pos.es.branch.util;

import com.nortic.pos.es.branch.rest.gen.model.RestModelBranch;
import com.nortic.pos.es.branch.rest.gen.model.RestModelStore;
import com.nortic.pos.models.dto.BranchDTO;
import com.nortic.pos.models.dto.StoreDTO;
import com.nortic.pos.models.entity.BranchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestMapper {
    @Mapping(target = "store", source = "storeId")
    BranchDTO restModelToDTO(RestModelBranch restModelBranch);
    BranchEntity dtoToEntity(BranchDTO branchDTO);
    BranchDTO entityToDTO(BranchEntity branchEntity);
    @Mapping(target = "storeId", source = "store")
    RestModelBranch dtoToRestModel(BranchDTO branchDTO);

    default StoreDTO mapStoreId(RestModelStore storeId) {
        if (storeId == null) {
            return null;
        }
        return StoreDTO.builder()
                .id(storeId.getId())
                .build();
    }


}
