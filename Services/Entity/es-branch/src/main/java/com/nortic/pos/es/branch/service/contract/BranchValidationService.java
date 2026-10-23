package com.nortic.pos.es.branch.service.contract;

import com.nortic.pos.es.branch.model.dto.BranchDTO;
import com.nortic.pos.es.branch.model.entity.BranchEntity;

import java.util.UUID;

public interface BranchValidationService {

    BranchEntity validateAndGetBranch(UUID id);

    void validateNewBranch(BranchDTO branchDTO);

    void validateBranchUpdate(UUID id, BranchDTO branchDTO);

}
