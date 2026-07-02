package com.nortic.pos.es.user.dto;

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
