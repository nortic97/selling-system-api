package com.nortic.pos.ts.platforminitializer.infrastructure.util;

import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.models.entity.StoreEntity;

public class BranchGenerator {
    public static BranchEntity generateInitialBranch(StoreEntity store) {
       return BranchEntity.builder()
               .id(Constants.GLOBAL_UUID)
                .store(store)
                .name("Platform Branch")
                .address("Platform Branch Address")
                .phoneNumber("Platform Branch Phone Number")
                .email("Platform Branch Email")
                .build();
    }
}
