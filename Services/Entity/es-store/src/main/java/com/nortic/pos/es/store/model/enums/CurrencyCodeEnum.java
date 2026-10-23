package com.nortic.pos.es.store.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyCodeEnum {

    COP("COP"),

    USD("USD"),

    EUR("EUR");

    private final String value;

}
