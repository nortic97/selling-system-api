package com.nortic.pos.es.role.service;

import com.nortic.pos.es.role.exception.BusinessException;
import com.nortic.pos.es.role.exception.ExceptionCode;
import com.nortic.pos.es.role.model.dto.FieldErrorDetailResponseDTO;
import com.nortic.pos.es.role.model.dto.RoleDTO;
import com.nortic.pos.es.role.model.entity.RoleEntity;
import com.nortic.pos.es.role.model.enums.ValidationFieldEnum;
import com.nortic.pos.es.role.repository.RoleRepository;
import com.nortic.pos.es.role.repository.StoreRepository;
import com.nortic.pos.es.role.service.contract.RoleValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleValidationServiceImpl implements RoleValidationService {

    private final RoleRepository roleRepository;
    private final StoreRepository storeRepository;

    @Override
    public RoleEntity validateAndGetRole(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.ROLE_NOT_FOUND));
    }

    @Override
    public void validateNewRole(RoleDTO roleDTO) {
        if (roleDTO.getStoreId() == null) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.STORE_ID.getField())
                            .message(ValidationFieldEnum.STORE_ID.getMessage())
                            .build())
            );
        }

        if (!storeRepository.existsById(roleDTO.getStoreId())) {
            throw new BusinessException(ExceptionCode.STORE_NOT_FOUND);
        }

        validateName(roleDTO.getName());

        String normalizedName = roleDTO.getName().trim();

        if (roleRepository.existsByStoreIdIdAndNameIgnoreCase(roleDTO.getStoreId(), normalizedName)) {
            throw new BusinessException(ExceptionCode.ROLE_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateRoleUpdate(UUID id, RoleDTO roleDTO) {
        if (roleDTO.getName() != null) {
            if (roleDTO.getName().isBlank()) {
                throw new BusinessException(
                        ExceptionCode.VALIDATION_FAILED,
                        List.of(FieldErrorDetailResponseDTO.builder()
                                .field(ValidationFieldEnum.NAME_BLANK.getField())
                                .message(ValidationFieldEnum.NAME_BLANK.getMessage())
                                .build())
                );
            }

            validateName(roleDTO.getName());

            var role = roleRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.ROLE_NOT_FOUND));
            String normalizedName = roleDTO.getName().trim();

            if (roleRepository.existsByStoreIdIdAndNameIgnoreCaseAndIdNot(role.getStoreId().getId(), normalizedName, id)) {
                throw new BusinessException(ExceptionCode.ROLE_NAME_ALREADY_EXISTS);
            }
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.NAME.getField())
                            .message(ValidationFieldEnum.NAME.getMessage())
                            .build())
            );
        }

        if (name.length() < 3) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.NAME_TOO_SHORT.getField())
                            .message(ValidationFieldEnum.NAME_TOO_SHORT.getMessage())
                            .build())
            );
        }

        if (name.length() > 100) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field(ValidationFieldEnum.NAME_TOO_LONG.getField())
                            .message(ValidationFieldEnum.NAME_TOO_LONG.getMessage())
                            .build())
            );
        }
    }
}
