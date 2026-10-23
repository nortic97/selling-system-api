package com.nortic.pos.es.branch.service.contract;

import com.nortic.pos.es.branch.model.dto.*;

import java.util.UUID;

public interface BranchService {

    BranchDTO getBranch(RequestContextDTO requestContextDTO, UUID id);

    BranchDTO createBranch(RequestContextDTO requestContextDTO, BranchDTO branchDTO);

    PaginatedBranchResponseDTO getAllBranches(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    void deleteBranch(RequestContextDTO requestContextDTO, UUID id);

    BranchDTO restoreBranch(RequestContextDTO requestContextDTO, UUID id);

    BranchDTO updateBranch(RequestContextDTO requestContextDTO, UUID id, BranchDTO branchDTO);

    BranchDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, StatusRequest statusRequest);

}
