package com.nortic.pos.es.branch.service;

import com.nortic.pos.es.branch.exception.BusinessException;
import com.nortic.pos.es.branch.exception.ExceptionCode;
import com.nortic.pos.es.branch.model.dto.BranchDTO;
import com.nortic.pos.es.branch.model.dto.FieldErrorDetailResponseDTO;
import com.nortic.pos.es.branch.model.entity.BranchEntity;
import com.nortic.pos.es.branch.model.enums.ValidationFieldEnum;
import com.nortic.pos.es.branch.repository.BranchRepository;
import com.nortic.pos.es.branch.repository.StoreRepository;
import com.nortic.pos.es.branch.service.contract.BranchValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchValidationServiceImpl implements BranchValidationService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;

    @Override
    public BranchEntity validateAndGetBranch(UUID id) {
        return branchRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.BRANCH_NOT_FOUND));
    }

    @Override
    public void validateNewBranch(BranchDTO branchDTO) {
        if (branchDTO.getStoreId() == null) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.STORE_ID.getField())
                            .message(ValidationFieldEnum.STORE_ID.getMessage())
                            .build())
            );
        }

        if (!storeRepository.existsById(branchDTO.getStoreId())) {
            throw new BusinessException(ExceptionCode.STORE_NOT_FOUND);
        }

        if (branchDTO.getName() == null || branchDTO.getName().isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.NAME.getField())
                            .message(ValidationFieldEnum.NAME.getMessage())
                            .build())
            );
        }

        String normalizedName = branchDTO.getName().trim();

        if (branchRepository.existsByStoreIdIdAndNameIgnoreCase(branchDTO.getStoreId(), normalizedName)) {
            throw new BusinessException(ExceptionCode.BRANCH_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateBranchUpdate(UUID id, BranchDTO branchDTO) {
        if (branchDTO.getName() != null && branchDTO.getName().isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.NAME_BLANK.getField())
                            .message(ValidationFieldEnum.NAME_BLANK.getMessage())
                            .build())
            );
        }

        if (branchDTO.getName() != null) {
            String normalizedName = branchDTO.getName().trim();
            var existingBranch = branchRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.BRANCH_NOT_FOUND));
            UUID storeId = existingBranch.getStoreId().getId();

            if (branchRepository.existsByStoreIdIdAndNameIgnoreCaseAndIdNot(storeId, normalizedName, id)) {
                throw new BusinessException(ExceptionCode.BRANCH_NAME_ALREADY_EXISTS);
            }
        }
    }
}
