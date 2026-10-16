package com.nortic.pos.es.feature.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeatureTypeEnum {

    BOOLEAN("BOOLEAN"),

    LIMIT("LIMIT"),

    UNLIMITED("UNLIMITED");

    private final String value;

}
