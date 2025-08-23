package com.nortic.pos.es.users.util;

import com.nortic.pos.es.users.model.RestModelPaginatedUsers;
import com.nortic.pos.es.users.model.RestModelUser;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;
import com.nortic.pos.models.entity.UserEntity;
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
