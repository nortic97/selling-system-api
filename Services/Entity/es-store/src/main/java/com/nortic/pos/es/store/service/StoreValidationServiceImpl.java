package com.nortic.pos.es.store.service;

import com.nortic.pos.es.store.exception.BusinessException;
import com.nortic.pos.es.store.exception.ExceptionCode;
import com.nortic.pos.es.store.model.dto.FieldErrorDetailResponseDTO;
import com.nortic.pos.es.store.model.dto.StoreDTO;
import com.nortic.pos.es.store.model.entity.StoreEntity;
import com.nortic.pos.es.store.repository.StoreRepository;
import com.nortic.pos.es.store.repository.UserRepository;
import com.nortic.pos.es.store.service.contract.StoreValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreValidationServiceImpl implements StoreValidationService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public StoreEntity validateAndGetStore(UUID id) {
        return storeRepository.findById(id).orElseThrow(() -> new BusinessException(ExceptionCode.STORE_NOT_FOUND));
    }

    @Override
    public void validateNewStore(StoreDTO storeDTO) {
        if (storeDTO.getName() == null || storeDTO.getName().isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("name")
                            .message("Name is required and cannot be blank")
                            .build())
            );
        }

        if (storeDTO.getOwnerId() == null) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("ownerId")
                            .message("Owner ID is required")
                            .build())
            );
        }

        if (storeDTO.getTaxType() == null) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("taxType")
                            .message("Tax type is required")
                            .build())
            );
        }

        if (storeDTO.getTaxId() == null || storeDTO.getTaxId().isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("taxId")
                            .message("Tax ID is required and cannot be blank")
                            .build())
            );
        }

        if (!userRepository.existsById(storeDTO.getOwnerId())) {
            throw new BusinessException(ExceptionCode.OWNER_NOT_FOUND);
        }

        String normalizedTaxId = storeDTO.getTaxId().trim().toLowerCase();

        if (storeRepository.existsByTaxTypeAndTaxIdIgnoreCase(storeDTO.getTaxType(), normalizedTaxId)) {
            throw new BusinessException(ExceptionCode.TAX_ID_ALREADY_EXISTS);
        }

        if (storeDTO.getContactEmail() != null && !storeDTO.getContactEmail().isBlank()) {
            String normalizedEmail = storeDTO.getContactEmail().trim().toLowerCase();
            if (storeRepository.existsByContactEmailIgnoreCase(normalizedEmail)) {
                throw new BusinessException(ExceptionCode.EMAIL_ALREADY_EXISTS);
            }
        }
    }

    @Override
    public void validateStoreUpdate(UUID id, StoreDTO storeDTO) {
        if (storeDTO.getName() != null && storeDTO.getName().isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("name")
                            .message("Name cannot be blank")
                            .build())
            );
        }

        if (storeDTO.getTaxType() == null) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("taxType")
                            .message("Tax type is required")
                            .build())
            );
        }

        if (storeDTO.getTaxId() != null && storeDTO.getTaxId().isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("taxId")
                            .message("Tax ID cannot be blank")
                            .build())
            );
        }

        if (storeDTO.getTaxType() != null && storeDTO.getTaxId() != null) {
            String normalizedTaxId = storeDTO.getTaxId().trim().toLowerCase();

            if (storeRepository.existsByTaxTypeAndTaxIdIgnoreCaseAndIdNot(storeDTO.getTaxType(), normalizedTaxId, id)) {
                throw new BusinessException(ExceptionCode.TAX_ID_ALREADY_EXISTS);
            }
        }
    }

    @Override
    public void validateEmail(UUID id, String email) {
        if (email == null || email.isBlank()) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("email")
                            .message("Email is required and cannot be blank")
                            .build())
            );
        }
        String normalizedEmail = email.trim().toLowerCase();
        if (storeRepository.existsByContactEmailIgnoreCaseAndIdNot(normalizedEmail, id)) {
            throw new BusinessException(ExceptionCode.EMAIL_ALREADY_EXISTS);
        }
    }

    @Override
    public void validatePhone(UUID id, String phone) {
        if (phone == null || phone.isBlank() || phone.trim().length() > 20) {
            throw new BusinessException(
                    ExceptionCode.VALIDATION_FAILED,
                    List.of(FieldErrorDetailResponseDTO.builder()
                            .field("phone")
                            .message("Phone is required and cannot exceed 20 characters")
                            .build())
            );
        }
    }
}
