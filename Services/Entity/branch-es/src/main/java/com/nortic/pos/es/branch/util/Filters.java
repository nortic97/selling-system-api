package com.nortic.pos.es.branch.util;

import com.nortic.pos.models.entity.BranchEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class Filters {

    public static Specification<BranchEntity> storeIdEquals(UUID storeId) {
        return (root, query, builder) -> builder.equal(root.get("store").get("id"), storeId);
    }

    public static Specification<BranchEntity> nameContains(String name) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<BranchEntity> isActiveEquals(Boolean isActive) {
        return (root, query, builder) ->
                builder.equal(root.get("isActive"), isActive);
    }
}
