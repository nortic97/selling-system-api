package com.nortic.pos.ms.auth.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccessTypeEnum {
    SYSTEM("SYSTEM"),
    POS("POS"),
    ADMIN("ADMIN");

    private final String value;
}
