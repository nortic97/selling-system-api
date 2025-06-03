package com.nortic.pos.ts.platforminitializer.infrastructure.util;

import com.nortic.pos.models.entity.StoreEntity;

public class StoreGenerator {
    public static StoreEntity generateInitialStore(){
        return StoreEntity.builder()
                .taxId("1069759007")
                .name("Nortic")
                .logo(null)
                .description("Initial Store to Platform")
                .slogan(null)
                .address("Chinauta - Fusagasuga")
                .country("Colombia")
                .stateProvince("Cundinamarca")
                .city("Fusagasuga")
                .phoneNumber("(+57) 3508082033")
                .email("nortic@gmail.com")
                .isActive(true)
                .build();
    }
}
