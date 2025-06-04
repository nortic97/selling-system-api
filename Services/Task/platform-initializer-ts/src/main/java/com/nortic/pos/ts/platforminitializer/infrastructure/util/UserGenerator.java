package com.nortic.pos.ts.platforminitializer.infrastructure.util;

import com.nortic.pos.models.entity.BranchEntity;
import com.nortic.pos.models.entity.RoleEntity;
import com.nortic.pos.models.entity.StoreEntity;
import com.nortic.pos.models.entity.UserEntity;
import com.nortic.pos.models.enums.AccountTypeEnum;
import com.nortic.pos.models.enums.DocumentTypeEnum;

import java.time.LocalDate;

public class UserGenerator {
    public static UserEntity generateInitialUser(StoreEntity store, BranchEntity branch, RoleEntity role) {
        return UserEntity.builder()
                .id(Constants.GLOBAL_UUID)
                .store(store)
                .branch(branch)
                .firstName("Jose")
                .lastName("Nova")
                .documentType(DocumentTypeEnum.CC)
                .documentNumber("1069759007")
                .userName("jdnova")
                .password(PasswordUtils.encryptPassword("ThomasNG1512-*"))
                .email("nortic97@gamail.com")
                .phone("(+57) 3508082033")
                .role(role)
                .accountType(AccountTypeEnum.INTERNAL)
                .isPrincipalAdmin(true)
                .address("Av. Siempre Viva 123")
                .birthDate(LocalDate.of(1997, 5, 22))
                .photo(null)
                .build();
    }
}
