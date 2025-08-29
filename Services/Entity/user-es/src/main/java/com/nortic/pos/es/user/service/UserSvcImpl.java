package com.nortic.pos.es.user.service;

import com.nortic.pos.es.user.repository.UserRepository;
import com.nortic.pos.es.user.util.CustomException;
import com.nortic.pos.es.user.util.ServiceMapper;
import com.nortic.pos.es.user.util.UserFilters;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.entity.UserEntity;
import com.nortic.pos.models.enums.AccountTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSvcImpl implements IUserSvc {

    private final ServiceMapper serviceMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Create User Service Start...");
        log.info("Creating new user");

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encryptedPassword);
            log.info("Password encrypted successfully");
        }
        
        UserEntity user = serviceMapper.dtoToEntity(userDTO);
        UserDTO result = serviceMapper.entityToDTO(userRepository.save(user));
        log.info("Create User Service End...");
        return result;
    }

    @Override
    public UserDTO getUser(UUID id) {
        log.info("Get User Service Start...");
        log.info("Retrieving user with id {}", id);
        UserDTO result = userRepository.findById(id)
                .map(serviceMapper::entityToDTO)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));
        log.info("Get User Service End...");
        return result;
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        log.info("Update User Service Start...");
        log.info("Updating user with id {}", id);

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUserName(userDTO.getUserName());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty() && 
            !passwordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())) {
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            userEntity.setPassword(encryptedPassword);
            log.info("Password updated and encrypted");
        }
        
        userEntity.setDocumentNumber(userDTO.getDocumentNumber());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhoto(userDTO.getPhoto());
        userEntity.setAccountType(userDTO.getAccountType());

        UserDTO result = serviceMapper.entityToDTO(userRepository.save(userEntity));
        log.info("Update User Service End...");
        return result;
    }

    @Override
    public void deleteUser(UUID id) {
        log.info("Delete User Service Start...");
        log.info("Deleting user with id {}", id);
        if (!userRepository.existsById(id)) {
            throw new CustomException(404, "ID: " + id + " Not Found");
        }
        userRepository.deleteById(id);
        log.info("Delete User Service End...");
    }

    @Override
    public void softDeleteUser(UUID id) {
        log.info("Soft Delete User Service Start...");
        log.info("Mark as deleted user with id {}", id);

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        if (userEntity.getDeletedAt() != null) {
            log.error("User with ID: {} is already deleted", id);
            throw new CustomException(409, "User with ID: " + id + " is already deleted");
        }

        userEntity.markAsDeleted();

        userRepository.save(userEntity);
        log.info("Soft Delete User Service End...");
    }

    @Override
    public void changeUserStatus(UUID id, Boolean status) {
        log.info("Change User Status Service Start...");
        log.info("Changing user status with id {} to status {}", id, status);

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(404, "ID: " + id + " Not Found"));

        if (Objects.equals(userEntity.getIsActive(), status)) {
            log.warn("User with id {} already has status {}", id, status);
            throw new CustomException(409, "User with id " + id + " already has status " + status);
        }

        userEntity.changeStatus(status);
        userRepository.save(userEntity);
        log.info("Change User Status Service End...");
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable, UUID storeId, UUID branchId, UUID roleId, AccountTypeEnum accountType, Boolean isActive, String search) {
        log.info("Get All Users Service Start...");
        
        Specification<UserEntity> spec = buildSpecification(storeId, branchId, roleId, accountType, isActive, search, false);
        
        Page<UserDTO> result = userRepository.findAll(spec, pageable)
                .map(serviceMapper::entityToDTO);
        
        log.info("Get All Users Service End...");
        return result;
    }

    @Override
    public Page<UserDTO> getDeletedUsers(Pageable pageable, UUID storeId, UUID branchId, UUID roleId, AccountTypeEnum accountType, Boolean isActive, String search) {
        log.info("Get Deleted Users Service Start...");
        
        Specification<UserEntity> spec = buildSpecification(storeId, branchId, roleId, accountType, isActive, search, true);
        
        Page<UserDTO> result = userRepository.findAll(spec, pageable)
                .map(serviceMapper::entityToDTO);
        
        log.info("Get Deleted Users Service End...");
        return result;
    }

    private Specification<UserEntity> buildSpecification(UUID storeId, UUID branchId, UUID roleId,
                                                        AccountTypeEnum accountType, Boolean isActive,
                                                        String search, boolean includeDeleted) {
        Specification<UserEntity> spec = Specification.where(null);
        
        if (storeId != null) {
            spec = spec.and(UserFilters.storeIdEquals(storeId));
        }
        
        if (branchId != null) {
            spec = spec.and(UserFilters.branchIdEquals(branchId));
        }
        
        if (roleId != null) {
            spec = spec.and(UserFilters.roleIdEquals(roleId));
        }
        
        if (accountType != null) {
            spec = spec.and(UserFilters.accountTypeEquals(accountType));
        }
        
        if (isActive != null) {
            spec = spec.and(UserFilters.isActiveEquals(isActive));
        }
        
        if (search != null && !search.trim().isEmpty()) {
            spec = spec.and(UserFilters.searchInFields(search));
        }
        
        if (includeDeleted) {
            spec = spec.and(UserFilters.isDeleted());
        } else {
            spec = spec.and(UserFilters.isNotDeleted());
        }
        
        return spec;
    }
}
