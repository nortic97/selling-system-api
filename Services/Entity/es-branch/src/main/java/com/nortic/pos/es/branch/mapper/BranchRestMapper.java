package com.nortic.pos.es.branch.mapper;

import com.nortic.pos.es.branch.model.dto.BranchDTO;
import com.nortic.pos.es.branch.model.dto.PaginatedBranchResponseDTO;
import com.nortic.pos.es.branch.model.dto.StatusRequest;
import com.nortic.pos.es.branch.rest.gen.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class BranchRestMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract BranchDTO toBranchDTO(RestModelBranchRequestDTO branch);

    public abstract RestModelBranchResponseDTO toBranchResponseDTO(BranchDTO branch);

    public abstract RestModelPaginatedBranchResponseDTO toListBranchesResponseDTO(PaginatedBranchResponseDTO paginatedBranchResponseDTO);

    public abstract StatusRequest toStatusRequest(RestModelStatusRequest restModelStatusRequest);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "storeId", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract BranchDTO toUpdateBranchRequestDTO(RestModelUpdateBranchRequestDTO restModelUpdateBranchRequestDTO);
}
