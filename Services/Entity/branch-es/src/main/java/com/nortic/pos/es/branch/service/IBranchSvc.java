package com.nortic.pos.es.branch.service;

import com.nortic.pos.models.dto.BranchDTO;
import com.nortic.pos.models.dto.BranchPaginationDTO;

import java.util.UUID;

public interface IBranchSvc {
    BranchDTO createBranch(BranchDTO branchDTO);
    BranchDTO getBranchById(UUID id);
    BranchDTO updateBranch(UUID id, BranchDTO branchDTO);
    void deleteBranch(UUID id);
    void softDeleteBranch(UUID id);
    void changeBranchStatus(UUID id, Boolean status);
    BranchPaginationDTO listBranchesByStore(UUID storeId,Integer page,Integer limit,String name,Boolean isActive);
}
