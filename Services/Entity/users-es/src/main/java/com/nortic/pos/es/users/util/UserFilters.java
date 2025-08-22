package com.nortic.pos.es.users.util;

import com.nortic.pos.models.entity.UserEntity;
import com.nortic.pos.models.enums.AccountTypeEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UserFilters {

    public static Specification<UserEntity> storeIdEquals(UUID storeId) {
        return (root, query, builder) -> builder.equal(root.get("store").get("id"), storeId);
    }

    public static Specification<UserEntity> branchIdEquals(UUID branchId) {
        return (root, query, builder) -> builder.equal(root.get("branch").get("id"), branchId);
    }

    public static Specification<UserEntity> roleIdEquals(UUID roleId) {
        return (root, query, builder) -> builder.equal(root.get("role").get("id"), roleId);
    }

    public static Specification<UserEntity> accountTypeEquals(AccountTypeEnum accountType) {
        return (root, query, builder) -> builder.equal(root.get("accountType"), accountType);
    }

    public static Specification<UserEntity> isActiveEquals(Boolean isActive) {
        return (root, query, builder) -> builder.equal(root.get("isActive"), isActive);
    }

    public static Specification<UserEntity> searchInFields(String search) {
        return (root, query, builder) -> {
            String searchPattern = "%" + search.toLowerCase() + "%";
            return builder.or(
                builder.like(builder.lower(root.get("firstName")), searchPattern),
                builder.like(builder.lower(root.get("lastName")), searchPattern),
                builder.like(builder.lower(root.get("email")), searchPattern),
                builder.like(builder.lower(root.get("userName")), searchPattern)
            );
        };
    }

    public static Specification<UserEntity> isNotDeleted() {
        return (root, query, builder) -> builder.isNull(root.get("deletedAt"));
    }

    public static Specification<UserEntity> isDeleted() {
        return (root, query, builder) -> builder.isNotNull(root.get("deletedAt"));
    }
}
