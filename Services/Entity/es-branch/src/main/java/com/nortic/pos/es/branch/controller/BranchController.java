package com.nortic.pos.es.branch.controller;

import com.nortic.pos.es.branch.mapper.BranchRestMapper;
import com.nortic.pos.es.branch.model.dto.RequestContextDTO;
import com.nortic.pos.es.branch.model.dto.SearchParamsDTO;
import com.nortic.pos.es.branch.rest.gen.api.BranchApi;
import com.nortic.pos.es.branch.rest.gen.model.*;
import com.nortic.pos.es.branch.service.contract.BranchService;
import com.nortic.pos.es.branch.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BranchController implements BranchApi {

    private final BranchService branchService;
    private final BranchRestMapper branchRestMapper;

    @Override
    public ResponseEntity<RestModelBranchResponseDTO> changeStatus(UUID correlationId, String consumerId, UUID id, RestModelStatusRequest restModelStatusRequest) {
        log.info("Change status controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var statusRequest = branchRestMapper.toStatusRequest(restModelStatusRequest);

        var branchDTO = branchService.changeStatus(requestContextDTO, id, statusRequest);

        log.info("Change status controller completed ... ");

        return ResponseEntity.ok(branchRestMapper.toBranchResponseDTO(branchDTO));
    }

    @Override
    public ResponseEntity<RestModelBranchResponseDTO> createBranch(UUID correlationId, String consumerId, RestModelBranchRequestDTO restModelBranchRequestDTO) {
        log.info("Create branch controller ...");

        var branchDTO = branchRestMapper.toBranchDTO(restModelBranchRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdBranchDTO = branchService.createBranch(requestContextDTO, branchDTO);

        log.info("Create branch controller completed ...");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(branchRestMapper.toBranchResponseDTO(createdBranchDTO));
    }

    @Override
    public ResponseEntity<Void> deleteBranch(UUID correlationId, String consumerId, UUID id) {
        log.info("Delete branch controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        branchService.deleteBranch(requestContextDTO, id);

        log.info("Delete branch controller completed ...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedBranchResponseDTO> getAllBranches(UUID correlationId, String consumerId, Integer page, Integer size, @Nullable List<String> sort) {
        log.info("List branches controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var branchesDTO = branchService.getAllBranches(requestContextDTO, pagination);

        log.info("List branches controller completed ...");

        return ResponseEntity.ok(branchRestMapper.toListBranchesResponseDTO(branchesDTO));
    }

    @Override
    public ResponseEntity<RestModelBranchResponseDTO> getBranch(UUID correlationId, String consumerId, UUID id) {
        log.info("Get branch controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var branchDTO = branchService.getBranch(requestContextDTO, id);

        log.info("Get branch controller completed ...");
        return ResponseEntity.ok(branchRestMapper.toBranchResponseDTO(branchDTO));
    }

    @Override
    public ResponseEntity<RestModelBranchResponseDTO> restoreBranch(UUID correlationId, String consumerId, UUID id) {
        log.info("Restore branch controller ...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var branchDTO = branchService.restoreBranch(requestContextDTO, id);

        log.info("Restore branch controller completed ...");

        return ResponseEntity.ok(branchRestMapper.toBranchResponseDTO(branchDTO));
    }

    @Override
    public ResponseEntity<RestModelBranchResponseDTO> updateBranch(UUID correlationId, String consumerId, UUID id, RestModelUpdateBranchRequestDTO restModelUpdateBranchRequestDTO) {
        log.info("Update branch controller ... ");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var branchDTO = branchRestMapper.toUpdateBranchRequestDTO(restModelUpdateBranchRequestDTO);

        var updatedBranchDTO = branchService.updateBranch(requestContextDTO, id, branchDTO);

        log.info("Update branch controller completed ... ");

        return ResponseEntity.ok(branchRestMapper.toBranchResponseDTO(updatedBranchDTO));
    }
}
