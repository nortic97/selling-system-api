package com.nortic.pos.es.branch.util;

import com.nortic.pos.es.branch.rest.gen.model.RestModelBranch;
import com.nortic.pos.es.branch.rest.gen.model.RestModelBranchPagination;
import com.nortic.pos.models.dto.BranchDTO;
import com.nortic.pos.models.dto.BranchPaginationDTO;
import com.nortic.pos.models.entity.BranchEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestMapper {
    BranchDTO restModelToDTO(RestModelBranch restModelBranch);
    BranchEntity dtoToEntity(BranchDTO branchDTO);
    BranchDTO entityToDTO(BranchEntity branchEntity);
    RestModelBranch dtoToRestModel(BranchDTO branchDTO);
    List<BranchDTO> entityListToDTOList(List<BranchEntity> entityList);
    RestModelBranchPagination restPaginationToDTO(BranchPaginationDTO branchPaginationDTO);
}
