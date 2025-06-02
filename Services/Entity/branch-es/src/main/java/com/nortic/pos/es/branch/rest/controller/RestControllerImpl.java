package com.nortic.pos.es.branch.rest.controller;

import com.nortic.pos.es.branch.rest.gen.api.BranchApi;
import com.nortic.pos.es.branch.rest.gen.model.RestModelBranch;
import com.nortic.pos.es.branch.rest.gen.model.RestModelBranchPagination;
import com.nortic.pos.es.branch.rest.gen.model.RestModelChangeBranchStatusRequest;
import com.nortic.pos.es.branch.service.BranchSvcImpl;
import com.nortic.pos.es.branch.util.RestMapper;
import com.nortic.pos.models.dto.BranchDTO;
import com.nortic.pos.models.dto.BranchPaginationDTO;
import com.nortic.pos.models.dto.StoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class RestControllerImpl implements BranchApi {

    private final RestMapper restMapper;
    private final BranchSvcImpl branchSvc;

    RestControllerImpl(RestMapper restMapper, BranchSvcImpl branchSvc, BranchSvcImpl branchSvcImpl) {
        this.restMapper = restMapper;
        this.branchSvc = branchSvc;
    }

    @Override
    public ResponseEntity<Void> changeBranchStatus(UUID id, RestModelChangeBranchStatusRequest restModelChangeBranchStatusRequest) {
        log.info("Branch with id {} changed status ", id);
        branchSvc.changeBranchStatus(id, restModelChangeBranchStatusRequest.getIsActive());
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<RestModelBranch> createBranch(RestModelBranch restModelBranch) {
        log.info("Create Branch");
        BranchDTO branch = branchSvc.createBranch(restMapper.restModelToDTO(restModelBranch));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restMapper.dtoToRestModel(branch));
    }

    @Override
    public ResponseEntity<Void> deleteBranch(UUID id) {
        log.info("Delete Branch");
        branchSvc.deleteBranch(id);
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<RestModelBranch> getBranchById(UUID id) {
        log.info("Getting branch by id: {}", id);
        return ResponseEntity.ok(restMapper.dtoToRestModel(branchSvc.getBranchById(id)));
    }

    @Override
    public ResponseEntity<RestModelBranchPagination> listBranchesByStore(UUID storeId, Integer page, Integer limit, String name, Boolean isActive) {
        log.info("Getting branches by store: {}", storeId);
        BranchPaginationDTO pagination = branchSvc.listBranchesByStore(storeId, page, limit, name, isActive);
        return ResponseEntity.ok(restMapper.restPaginationToDTO(pagination));
    }

    @Override
    public ResponseEntity<Void> softDeleteBranch(UUID id) {
        log.info("Soft Delete Branch");
        branchSvc.softDeleteBranch(id);
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<RestModelBranch> updateBranch(UUID id, RestModelBranch restModelBranch) {
        log.info("Updating branch with id {}", id);
        BranchDTO branch = branchSvc.updateBranch(id, restMapper.restModelToDTO(restModelBranch));
        return ResponseEntity.ok(restMapper.dtoToRestModel(branch));
    }
}
