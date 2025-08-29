package com.nortic.pos.es.user.util;

import com.nortic.pos.es.user.model.RestModelPaginatedUsers;
import com.nortic.pos.es.user.model.RestModelUser;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;
import com.nortic.pos.models.enums.AccountTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RestMapper {
    // REST Model to DTO - mapeo directo de IDs
    @Mapping(target = "storeId", source = "storeId")
    @Mapping(target = "branchId", source = "branchId")
    @Mapping(target = "roleId", source = "roleId")
    @Mapping(target = "accountType", source = "accountType", qualifiedByName = "stringToAccountType")
    UserDTO restModelToDTO(RestModelUser restModelUser);

    // DTO to REST Model - mapeo directo de IDs
    @Mapping(target = "storeId", source = "storeId")
    @Mapping(target = "branchId", source = "branchId")
    @Mapping(target = "roleId", source = "roleId")
    RestModelUser dtoToRestModel(UserDTO userDTO);

    RestModelPaginatedUsers pageToRestModelPaginatedUsers(Page<UserDTO> page);

    @Named("uuidToStoreEntity")
    default StoreEntity uuidToStoreEntity(UUID storeId) {
        if (storeId == null) return null;
        return StoreEntity.builder().id(storeId).build();
    }

    @Named("uuidToBranchEntity")
    default BranchEntity uuidToBranchEntity(UUID branchId) {
        if (branchId == null) return null;
        return BranchEntity.builder().id(branchId).build();
    }

    @Named("uuidToRoleEntity")
    default RoleEntity uuidToRoleEntity(UUID roleId) {
        if (roleId == null) return null;
        return RoleEntity.builder().id(roleId).build();
    }

    @Named("stringToAccountType")
    default AccountTypeEnum stringToAccountType(String accountType) {
        if (accountType == null) return null;
        return AccountTypeEnum.fromValue(accountType);
    }
}
