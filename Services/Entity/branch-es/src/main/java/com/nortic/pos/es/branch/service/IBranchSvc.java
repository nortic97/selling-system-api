package com.nortic.pos.es.branch.service;

import com.nortic.pos.models.dto.BranchDTO;

import java.util.UUID;

public interface IBranchSvc {
    BranchDTO createBranch(BranchDTO branchDTO);
    BranchDTO getBranchById(UUID id);
    BranchDTO updateBranch(UUID id, BranchDTO branchDTO);
    void deleteBranch(UUID id);
    void softDeleteBranch(UUID id);
    void changeBranchStatus(UUID id, Boolean status);
}
