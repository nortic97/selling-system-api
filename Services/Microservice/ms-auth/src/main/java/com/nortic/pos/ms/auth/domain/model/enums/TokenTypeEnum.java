package com.nortic.pos.ms.auth.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenTypeEnum {

    BEARER("Bearer");

    private final String value;

}
