package com.nortic.pos.es.branch.service;

import com.nortic.pos.es.branch.exception.BusinessException;
import com.nortic.pos.es.branch.exception.ExceptionCode;
import com.nortic.pos.es.branch.mapper.BranchEntityMapper;
import com.nortic.pos.es.branch.model.dto.*;
import com.nortic.pos.es.branch.repository.BranchRepository;
import com.nortic.pos.es.branch.service.contract.BranchService;
import com.nortic.pos.es.branch.service.contract.BranchValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchEntityMapper branchEntityMapper;
    private final BranchValidationService branchValidationService;

    @Override
    public BranchDTO getBranch(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Get branch by id {} service ...", id);

        var branch = branchValidationService.validateAndGetBranch(id);

        log.info("Get branch by id {} service completed ...", id);

        return branchEntityMapper.toBranchDTO(branch);
    }

    @Override
    public BranchDTO createBranch(RequestContextDTO requestContextDTO, BranchDTO branchDTO) {
        log.info("Create branch service ...");

        branchValidationService.validateNewBranch(branchDTO);

        var branchEntity = branchEntityMapper.toBranchEntity(branchDTO);
        branchEntity.setIsActive(Boolean.TRUE);

        var savedBranchEntity = branchRepository.save(branchEntity);

        log.info("Create branch service completed ...");

        return branchEntityMapper.toBranchDTO(savedBranchEntity);
    }

    @Override
    public PaginatedBranchResponseDTO getAllBranches(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("List branches service ...");

        var branches = branchRepository.findAll(searchParamsDTO.getPageable());

        log.info("List branches service completed ... ");

        return branchEntityMapper.toListBranchDTO(branches);
    }

    @Override
    @Transactional
    public void deleteBranch(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Delete branch service... ");

        var branchEntity = branchValidationService.validateAndGetBranch(id);

        branchRepository.delete(branchEntity);

        log.info("Delete branch service completed ... ");
    }

    @Override
    @Transactional
    public BranchDTO restoreBranch(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restore branch service ... ");

        var branch = branchRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new BusinessException(ExceptionCode.BRANCH_NOT_FOUND));

        branch.setIsActive(Boolean.TRUE);
        branch.setDeletedAt(null);

        log.info("Restore branch service completed ... ");
        return branchEntityMapper.toBranchDTO(branch);
    }

    @Override
    @Transactional
    public BranchDTO updateBranch(RequestContextDTO requestContextDTO, UUID id, BranchDTO branchDTO) {
        log.info("Update branch service ... ");

        var branchEntity = branchValidationService.validateAndGetBranch(id);

        branchValidationService.validateBranchUpdate(id, branchDTO);

        branchEntityMapper.updateBranchFromDTO(branchDTO, branchEntity);

        log.info("Update branch service completed ... ");

        return branchEntityMapper.toBranchDTO(branchEntity);
    }

    @Override
    @Transactional
    public BranchDTO changeStatus(RequestContextDTO requestContextDTO, UUID id, StatusRequest statusRequest) {
        log.info("Change status service ... ");

        var branchEntity = branchValidationService.validateAndGetBranch(id);

        branchEntity.setIsActive(statusRequest.getActive());

        log.info("Change status service completed ... ");

        return branchEntityMapper.toBranchDTO(branchEntity);
    }

}
