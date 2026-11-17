package com.nortic.pos.ms.auth.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentTypeEnum {

    CC("CC"),

    TI("TI"),

    DNI("DNI"),

    NIT("NIT"),

    PEP("PEP");

    private final String value;

}
