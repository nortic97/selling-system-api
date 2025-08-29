package com.nortic.pos.es.user.util;

import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;
import com.nortic.pos.models.entity.UserEntity;
import com.nortic.pos.models.enums.AccountTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    
    // DTO to Entity - convertir IDs a entidades
    @Mapping(target = "store", source = "storeId", qualifiedByName = "uuidToStoreEntity")
    @Mapping(target = "branch", source = "branchId", qualifiedByName = "uuidToBranchEntity")
    @Mapping(target = "role", source = "roleId", qualifiedByName = "uuidToRoleEntity")
    @Mapping(target = "userPlanPayments", ignore = true)
    @Mapping(target = "returns", ignore = true)
    @Mapping(target = "sale", ignore = true)
    UserEntity dtoToEntity(UserDTO userDTO);
    
    // Entity to DTO - extraer IDs de entidades
    @Mapping(target = "storeId", source = "store.id")
    @Mapping(target = "branchId", source = "branch.id")
    @Mapping(target = "roleId", source = "role.id")
    UserDTO entityToDTO(UserEntity userEntity);

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
