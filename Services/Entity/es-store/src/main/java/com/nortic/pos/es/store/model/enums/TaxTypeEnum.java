package com.nortic.pos.es.store.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaxTypeEnum {

    NIT("NIT"),

    RFC("RFC"),

    NIF("NIF"),

    CUIT("CUIT"),

    SSN("SSN"),

    RUT("RUT");

    private final String value;

}
